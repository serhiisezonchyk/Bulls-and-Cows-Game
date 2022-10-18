package com.example.bullsandcows.threads;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;

public class LooperThreadTask<T> extends BaseTask<T>{
    private static Handler taskHandler;

    static {
        new LooperThread(handler->taskHandler = handler).start();
    }
    private Callable<T> callable;

    private Runnable action;

     public LooperThreadTask(Callable<T> callable){
         this.callable = callable;
     }

    @Override
    protected void start() {
         action = () -> {
             try {
                 T result = callable.call();
                 publishSuccess(result);
             }catch (Exception e){
                publishError(e);
             }
         };
         taskHandler.post(action);
    }

    @Override
    protected void onCancelled() {
        if(action != null) taskHandler.removeCallbacks(action);
        action = null;
    }

    static  class LooperThread extends Thread{
         private HandlerListener listener;
         private LooperThread(HandlerListener listener){
             this.listener = listener;
         }
         @Override
         public void run() {
             Looper.prepare();
             Looper looper = Looper.myLooper();
             Handler handler = new Handler(looper);

             listener.onHandler(handler);
             listener = null;

             looper.loop();
         }
     }

     private  interface HandlerListener{
         void onHandler(Handler handler);
     }
}
