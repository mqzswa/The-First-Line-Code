package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityDiagnosticsManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;
    private IntentFilter intentFilter;
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiver localBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager=LocalBroadcastManager.getInstance(this);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent=new Intent("android.intent.action.LOCAL_BROADCAST");
//            sendBroadcast(intent);
//            sendOrderedBroadcast(intent,null);
            localBroadcastManager.sendBroadcast(intent);
        });

        intentFilter=new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver=new NetworkChangeReceiver();
//        localBroadcastManager.registerReceiver(networkChangeReceiver,intentFilter);
        intentFilter.addAction("android.intent.action.LOCAL_BROADCAST");
        localBroadcastReceiver=new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class  NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if((networkInfo != null) && networkInfo.isAvailable()){
                Toast.makeText(context, "network change turn on", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "network change turn off", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"This is Local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}