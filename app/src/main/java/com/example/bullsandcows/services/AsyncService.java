package com.example.bullsandcows.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bullsandcows.BuildConfig;
import com.example.bullsandcows.obs.App;
import com.example.bullsandcows.threads.LooperThreadTask;
import com.example.bullsandcows.threads.Task;
import com.example.bullsandcows.threads.TaskListener;

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

    private Task<Void> currentTask;

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
        switch (action) {
            case ACTION_GET_RAND_NUM:{
                currentTask = createGenerateTask(app,length);
                currentTask.execute(new TaskListener<Void>() {
                    @Override public void onSuccsess(Void result){}
                    @Override public void onError(Throwable error){}
                });
                break;
            }
            case ACTION_GET_BULLS:{
                int[] userString = intent.getIntArrayExtra(USER_NUM);
                int[] generatedString = intent.getIntArrayExtra(RAND_NUM);
                currentTask = createGetBullsTask(app,length,generatedString,userString);
                currentTask.execute(new TaskListener<Void>() {
                    @Override public void onSuccsess(Void result){}
                    @Override public void onError(Throwable error){}
                });
                break;
            }
            case ACTION_GET_COWS:{
                int[] userString = intent.getIntArrayExtra(USER_NUM);
                int[] generatedString = intent.getIntArrayExtra(RAND_NUM);
                currentTask = createGetCowsTask(app,length,generatedString,userString);
                currentTask.execute(new TaskListener<Void>() {
                    @Override public void onSuccsess(Void result){}
                    @Override public void onError(Throwable error){}
                });
                break;
            }
            default:
                throw new RuntimeException("Unknown action!");
        }
    }

    private Task<Void> createGenerateTask(App app, int length){
        return new LooperThreadTask<Void>(new GenerateNumCallable(app,TAG,length));
    }
    private Task<Void> createGetBullsTask(App app, int length, int[] generatedString, int [] userString){
        return new LooperThreadTask<Void>(new BullsAndCowsCallable(app,TAG,true,length,generatedString,userString));
    }
    private Task<Void> createGetCowsTask(App app, int length, int[] generatedString, int [] userString){
        return new LooperThreadTask<Void>(new BullsAndCowsCallable(app,TAG,false,length,generatedString,userString));
    }

}