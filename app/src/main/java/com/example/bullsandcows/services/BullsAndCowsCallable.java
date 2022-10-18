package com.example.bullsandcows.services;

import android.util.Log;

import com.example.bullsandcows.obs.App;

import java.util.concurrent.Callable;

public class BullsAndCowsCallable implements Callable<Void> {
    private App app;
    private String TAG;
    private  boolean bulls;
    private int lenght;
    private int[] generatedString;
    private int[] userString;

    public BullsAndCowsCallable(App app, String TAG, boolean bulls, int length, int[] generatedString, int[] userString) {
        this.app = app;
        this.TAG = TAG;
        this.bulls = bulls;
        this.lenght = length;
        this.generatedString = generatedString;
        this.userString = userString;
    }

    @Override
    public Void call() throws Exception {
        if(bulls){
            Log.d(TAG, "Job started: getBulls()");
            int  result = 0;
            for (int i = 0 ;i < lenght;i++)
                if(userString[i] == generatedString[i])
                    result++;
            Log.d(TAG, "Job finished, results: Bulls="
                    + result);
            app.onGetBulls(result);
        }else{
            Log.d(TAG, "Job started: getCows()");
            int result = 0;
            for (int i = 0 ;i < lenght;i++)
                for (int j = 0;j<lenght;j++)
                    if(i!=j)
                        if(userString[i]==generatedString[j])
                            result++;
            Log.d(TAG, "Job finished, results: Cows"
                    + result);
            app.onGetCows(result);
        }
        return null;
    }
}
