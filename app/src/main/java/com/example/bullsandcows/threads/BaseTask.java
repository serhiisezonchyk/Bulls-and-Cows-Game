package com.example.bullsandcows.threads;

import android.os.Handler;
import android.os.Looper;


public abstract class BaseTask<T> implements  Task<T> {
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private TaskListener<T> taskListener;

    private boolean executed;
    private boolean cancelled;

    @Override
    public void execute(TaskListener<T> listener) {
        if(executed) throw new RuntimeException("Task have been already executed");
        if(cancelled) return;
        executed = true;
        this.taskListener = listener;
        start();
    }

    @Override
    public void cancel() {
        if(!cancelled){
            cancelled = true;
            taskListener = null;
            onCancelled();
        }
    }

    protected final void publishSuccess(T result){
        runOnMainThread(()->{
            if(taskListener!=null) {
                taskListener.onSuccsess(result);
                taskListener = null;
            }
        });
    }

    protected final void publishError(Throwable error){
        runOnMainThread(()->{
            if(taskListener!=null){
                taskListener.onError(error);
                taskListener = null;
            }
        });
    }

    private void runOnMainThread(Runnable action){
        if(Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()){
            action.run();
        }else {
            handler.post(action);
        }
    }

    protected abstract void start();
    protected abstract void onCancelled();
}