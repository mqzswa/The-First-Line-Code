package com.example.servicebestpractice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.io.File;

public class MyService extends Service {

    private String downloadUrl;
    private DownloadTask downloadTask;
    private DownloadListener downloadListener=new DownloadListener() {
        @Override
        public void onProcess(int process) {
            getNotificationManager().notify(1,getNotification("Downloading...", process));
        }

        @Override
        public void onPause() {
            downloadTask=null;
            Toast.makeText(MyService.this,"Download pause",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onSuccess() {
            downloadTask=null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success!",-1));
            Toast.makeText(MyService.this,"Download success",Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onFailed() {
            downloadTask=null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed!",-1));
            Toast.makeText(MyService.this,"Download failed",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCanceled() {
            downloadTask=null;
            stopForeground(true);
            Toast.makeText(MyService.this,"Download canceled",Toast.LENGTH_SHORT).show();

        }
    };


    private Notification getNotification(String title,int process) {

        String CHANNEL_ID="1";
        String CHANNEL_NAME="CHANNEL_NAME";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"default");
        builder.setChannelId(CHANNEL_ID);
        builder.setContentTitle(title);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setSmallIcon(R.mipmap.ic_launcher);

        if(process>=0){
            builder.setContentText(process+"%");
            builder.setProgress(100,process,false);
        }
        Notification notification=builder.build();
        return notification;

    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }

    private DownloadBinder downloadBinder=new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if(downloadTask==null){
                downloadTask=new DownloadTask(downloadListener);
                downloadUrl=url;
                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(MyService.this,"Downloading...",Toast.LENGTH_SHORT).show();
            }

        }
        public void pauseDownload(){
            if(downloadTask!=null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if(downloadTask!=null){
                downloadTask.canceledDownload();
            }else{
                if(downloadUrl!=null){
                    String filename=downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file=new File(directory+filename);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(MyService.this,"Canceled Download!",Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}