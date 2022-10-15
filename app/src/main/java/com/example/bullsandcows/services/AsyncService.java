package com.example.bullsandcows.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bullsandcows.BuildConfig;
import com.example.bullsandcows.obs.App;

public class AsyncService extends IntentService {

    public static final String TAG = AsyncService.class.getSimpleName();

    public static final String ACTION_GET_RAND_NUM=
            BuildConfig.APPLICATION_ID + ".ACTION_GET_RAND_NUM";

    public static final String ACTION_GET_BULLS=
            BuildConfig.APPLICATION_ID + ".ACTION_GET_BULLS";

    public static final String ACTION_GET_COWS=
            BuildConfig.APPLICATION_ID + ".ACTION_GET_COWS";

    public static final String LENGTH = "LENGTH";
    public static final String USER_NUM = "USER_NUM";
    public static final String RAND_NUM = "RAND_NUM";



    private ServiceBullsAndCows serviceBullsAndCows= new ServiceBullsAndCows(TAG);

    public AsyncService() {
        super(AsyncService.class.getSimpleName());
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return super.onBind(intent);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) return;
        String action = intent.getAction();
        if (action == null) return;
        App app = (App) getApplicationContext();

        int length = intent.getIntExtra(LENGTH,4);
        try {
            switch (action) {
                case ACTION_GET_RAND_NUM:{
                    serviceBullsAndCows.getRandNum(app,length);
                    break;
                }
                case ACTION_GET_BULLS:{
                    int[] userString = intent.getIntArrayExtra(USER_NUM);
                    int[] generatedString = intent.getIntArrayExtra(RAND_NUM);
                    serviceBullsAndCows.getBulls(app,userString,generatedString,length);
                    break;
                }
                case ACTION_GET_COWS:{
                    int[] userString = intent.getIntArrayExtra(USER_NUM);
                    int[] generatedString = intent.getIntArrayExtra(RAND_NUM);
                    serviceBullsAndCows.getCows(app,userString,generatedString,length);
                    break;
                }
                default:
                    throw new RuntimeException("Unknown action!");
            }
        } catch (InterruptedException e) {
            Log.e(TAG, "Job has been interrupted");
        }
    }
}