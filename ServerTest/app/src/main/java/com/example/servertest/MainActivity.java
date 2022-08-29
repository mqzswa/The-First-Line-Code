package com.example.servertest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_start=findViewById(R.id.button_start);
        Button button_stop=findViewById(R.id.button_stop);
        Button button_bind=findViewById(R.id.button_bind);
        Button button_unbind=findViewById(R.id.button_unbind);
        Button button_intent_service=findViewById(R.id.button_start_intent_service);

        button_start.setOnClickListener(this);
        button_stop.setOnClickListener(this);
        button_bind.setOnClickListener(this);
        button_unbind.setOnClickListener(this);
        button_intent_service.setOnClickListener(this);
    }

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder=(MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProcess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_start:
                Intent intent_start=new Intent(this,MyService.class);
                startService(intent_start);
                break;

            case R.id.button_stop:
                Intent intent_stop=new Intent(this,MyService.class);
                stopService(intent_stop);
                break;

            case R.id.button_bind:
                Intent intent_bind=new Intent(this,MyService.class);
                bindService(intent_bind,serviceConnection,BIND_AUTO_CREATE);
                break;

            case R.id.button_unbind:
                unbindService(serviceConnection);

            case R.id.button_start_intent_service:
                Log.d("MainActivity:", "Thread id is:"+Thread.currentThread().getId());
                Intent intent=new Intent(this, MyIntentService.class);
                startService(intent);
                break;

            default:
                break;
        }
    }
}