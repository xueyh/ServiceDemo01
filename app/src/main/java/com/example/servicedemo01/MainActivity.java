package com.example.servicedemo01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager mLocalBroadcastManager;
    private DownloadStateReceiver mReceiver;
    private Button mButton;

    private Boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);

        Log.e("xxx", "------isLogin = " + isLogin);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.BROADCAST_ACTION);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mReceiver = new DownloadStateReceiver();
        mLocalBroadcastManager.registerReceiver(mReceiver, filter);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dataUrl = MainActivity.this.getFilesDir().getAbsolutePath();
                Intent mServiceIntent = new Intent(MainActivity.this, RSSPullService.class);
                mServiceIntent.setData(Uri.parse(dataUrl));
                MainActivity.this.startService(mServiceIntent);
            }
        });


    }


    // Broadcast receiver for receiving status updates from the IntentService
    private class DownloadStateReceiver extends BroadcastReceiver {
        // Prevents instantiation
        private DownloadStateReceiver() {
        }

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        @Override
        public void onReceive(Context context, Intent intent) {

        /*
         * Handle Intents here.
         */


            if (intent.getAction().equals(Constants.BROADCAST_ACTION)) {

                Log.e("TAG", "------" + intent.getExtras().getString(Constants.EXTENDED_DATA_STATUS));
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mLocalBroadcastManager !=null) {
            mLocalBroadcastManager.unregisterReceiver(mReceiver);
            mLocalBroadcastManager = null;
        }

    }
}
