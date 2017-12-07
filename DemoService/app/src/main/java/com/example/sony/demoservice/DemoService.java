package com.example.sony.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Sony on 12/7/2017.
 */

public class DemoService extends Service {

    public static String TAG = DemoService.class.toString();

    public DemoService() {
        super();
        Log.d(TAG, "cons");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "on Create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "on StartCommand");
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(MainActivity.bc1);
                broadcastIntent.putExtra("Data", "=))))))");
                sendBroadcast(broadcastIntent);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                broadcastIntent.setAction(MainActivity.bc2);
                broadcastIntent.putExtra("Data", 1234);
                sendBroadcast(broadcastIntent);


            }
        }).start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "on Destroy");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
