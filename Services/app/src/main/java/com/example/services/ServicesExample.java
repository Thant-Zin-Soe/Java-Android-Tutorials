package com.example.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServicesExample extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ServicesExample() {
        super("name");
    }

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service","Services is Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service","Services is Started");
        //stopSelf();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service","Services is Stopped");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service","onHandel method worked");
    }
}
// do not need to stop because IntendServices work it self when it is Done.