package com.example.bullsandcows.services;

import android.util.Log;

import com.example.bullsandcows.obs.App;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

public class GenerateNumCallable implements Callable<Void> {
    private App app;
    private String TAG;
    private int length;

    public GenerateNumCallable(App app, String TAG, int length) {
        this.app = app;
        this.TAG = TAG;
        this.length = length;
    }

    @Override
    public Void call() throws Exception {
        Log.d(TAG, "Job started: getRandNum");
        int [] arr = new int [10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        shuffleArray(arr);
        Log.d(TAG, "Job finished, results: "
                + Arrays.copyOfRange(arr,0,length).toString());
        app.onGetRand(Arrays.copyOfRange(arr,0,length));
        return null;
    }
    private static void shuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }
}
