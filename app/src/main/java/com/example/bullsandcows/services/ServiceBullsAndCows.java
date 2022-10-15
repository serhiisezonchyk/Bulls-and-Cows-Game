package com.example.bullsandcows.services;

import android.util.Log;

import com.example.bullsandcows.obs.App;

import java.util.Arrays;
import java.util.Random;

public class ServiceBullsAndCows {
    private static String tag;
    public  ServiceBullsAndCows(String tag){
        this.tag = tag;
    }

    public void getRandNum(App app,int num)
            throws InterruptedException{
        Log.d(tag, "Job started: getRandNum");
        int [] arr = new int [10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        shuffleArray(arr);
        Log.d(tag, "Job finished, results: "
                + Arrays.copyOfRange(arr,0,num).toString());
        app.onGetRand(Arrays.copyOfRange(arr,0,num));
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

    public void getBulls(App app,int[] userString, int[] generatedString, int lenght)
            throws InterruptedException{
        Log.d(tag, "Job started: getBulls()");
        int  result = 0;
        for (int i = 0 ;i < lenght;i++)
            if(userString[i] == generatedString[i])
                result++;
        Log.d(tag, "Job finished, results: Bulls="
                + result);
        app.onGetBulls(result);
    }

    //Отримання числа входжень
    public void getCows(App app,int[] userString, int[] generatedString, int lenght)
            throws InterruptedException{
        Log.d(tag, "Job started: getCows()");
        int result = 0;
        for (int i = 0 ;i < lenght;i++)
            for (int j = 0;j<lenght;j++)
                if(i!=j)
                    if(userString[i]==generatedString[j])
                        result++;
        Log.d(tag, "Job finished, results: Cows"
                + result);
        app.onGetCows(result);
    }
}