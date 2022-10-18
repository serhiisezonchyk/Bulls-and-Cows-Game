package com.example.bullsandcows.threads;

public interface TaskListener<T> {
    void onSuccsess(T result);
    void onError(Throwable error);
}
