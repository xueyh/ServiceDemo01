package com.example.servicedemo01;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class RSSPullService extends IntentService {


    public RSSPullService() {
        super("RSSPullService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String dataString = intent.getDataString();
        Log.e("TAG", "dataString = " + dataString);

        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);
        localIntent.putExtra(Constants.EXTENDED_DATA_STATUS, "OK");
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
