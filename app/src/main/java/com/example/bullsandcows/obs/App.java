package com.example.bullsandcows.obs;

import android.app.Application;
import android.os.Looper;
import android.os.Handler;
import java.util.HashSet;
import java.util.Set;


public class App extends Application {

    private Set<TaskListener> listeners = new HashSet<>();
    private Handler handler = new Handler(Looper.getMainLooper());

    public  void  addListener(TaskListener listener){
        this.listeners.add(listener);
    }
    public void removeListener(TaskListener listener){
        this.listeners.remove(listener);
    }

    public void onGetRand(int[] rand){
        handler.post(()->{
            for (TaskListener listener: listeners){
                listener.getRandNum(rand);
            }
        });
    }
    public void onGetCows(int cows){
        handler.post(()->{
            for (TaskListener listener: listeners){
                listener.getCows(cows);
            }
        });
    }
    public void onGetBulls(int bulls){
        handler.post(()->{
            for (TaskListener listener: listeners){
                listener.getBulls(bulls);
            }
        });
    }
}