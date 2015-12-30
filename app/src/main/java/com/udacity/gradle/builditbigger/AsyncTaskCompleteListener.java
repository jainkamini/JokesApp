package com.udacity.gradle.builditbigger;

/**
 * Created by Kamini on 12/30/2015.
 */
public interface AsyncTaskCompleteListener <T> {

    void onTaskComplete(T result);

    void onTaskBefore();

    void onAsyncExceptionRaised(Exception e);
}
