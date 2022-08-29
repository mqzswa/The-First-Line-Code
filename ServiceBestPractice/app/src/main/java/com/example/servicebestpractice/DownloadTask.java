package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    public static final int TYPE_SUCCESS=0;
    public static final int TYPE_FAILED=1;
    public static final int TYPE_PAUSE=2;
    public static final int TYPE_CANCELED=3;

    private DownloadListener downloadListener;

    private boolean isCanceled=false;
    private boolean isPause=false;

    private int lastprocess;

    public DownloadTask(DownloadListener listener){
        this.downloadListener=listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_PAUSE:
                downloadListener.onPause();
                break;
            case TYPE_CANCELED:
                downloadListener.onCanceled();
                break;
            default:
                break;
        }

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int process=values[0];
        if(process>lastprocess){
            lastprocess=process;
            downloadListener.onProcess(process);
        }
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream inputStream=null;
        RandomAccessFile randomAccessFile=null;
        File file=null;
        try {
            long downloadlength=0;
            String downloadurl=strings[0];
            String filename=downloadurl.substring(downloadurl.lastIndexOf("/"));
            String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file=new File(directory+filename);
            if(file.exists()){
                downloadlength=file.length();
            }
            long contentlength=getContentLength(downloadurl);
            if(contentlength==0){
                return TYPE_FAILED;
            }else if(contentlength==downloadlength){
                return TYPE_SUCCESS;
            }

            OkHttpClient okHttpClient=new OkHttpClient();
            Request request=new Request.Builder().addHeader("RANGE","bytes="+downloadlength+"-").url(downloadurl).build();
            Response response=okHttpClient.newCall(request).execute();
            if(response!=null){
                inputStream=response.body().byteStream();;
                randomAccessFile=new RandomAccessFile(file,"rw");
                randomAccessFile.seek(downloadlength);
                byte[]bytes=new byte[1024];
                int total=0;
                int len=0;
                while((len=inputStream.read(bytes))!=-1){
                    if(isCanceled){
                        return TYPE_CANCELED;
                    }else if(isPause){
                        return TYPE_PAUSE;
                    }else{
                        total+=len;
                        randomAccessFile.write(bytes,0,len);
                        int process=(int)((total+downloadlength)*100/contentlength);
                        publishProgress(process);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(inputStream!=null){
                    inputStream.close();
                }
                if(randomAccessFile!=null){
                    randomAccessFile.close();
                }
                if(isCanceled&&file!=null){
                    file.delete();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    public void pauseDownload(){
        isPause=true;
    }

    public void canceledDownload(){
        isCanceled=true;
    }

    private long getContentLength(String downloadurl) {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(downloadurl).build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            if(response!=null&&response.isSuccessful()){
                long contentlength=response.body().contentLength();
                response.close();
                return contentlength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
