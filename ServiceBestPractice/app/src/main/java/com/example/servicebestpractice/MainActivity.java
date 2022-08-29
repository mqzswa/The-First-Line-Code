package com.example.servicebestpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=findViewById(R.id.button_start);
        Button button2=findViewById(R.id.button_pause);
        Button button3=findViewById(R.id.button_cancel);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        Intent intent=new Intent(this,MyService.class);
        startService(intent);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{"Manifest.permission.WRITE_EXTERNAL_STORAGE"},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "拒绝权限将无法使用", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start:
                //String url="https://dldir1.qq.com/qqfile/qq/PCQQ9.6.6/QQ9.6.6.28788.exe";
                //String url="https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fwww.uimaker.com%252Fuploads%252Fallimg%252F20140620%252F1403226132285171.png%26refer%3Dhttp%253A%252F%252Fwww.uimaker.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1662618194%26t%3D078e150b38619d4f8de0d4e24c7a234e&thumburl=https%3A%2F%2Fimg0.baidu.com%2Fit%2Fu%3D2872683497%2C3212875074%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DPNG%3Fw%3D667%26h%3D500";
                String url="https://www.runoob.com/wp-content/uploads/2015/09/DownLoadDemo1.zip";
                downloadBinder.startDownload(url);
                break;
            case R.id.button_pause:
                downloadBinder.pauseDownload();
                break;
            case R.id.button_cancel:
                downloadBinder.cancelDownload();
                break;
            default:
                break;
        }
    }
}