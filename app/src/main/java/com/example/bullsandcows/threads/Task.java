package com.example.bullsandcows.threads;


public interface Task <T>{
    void execute(TaskListener<T> listener);
    void cancel();
}
