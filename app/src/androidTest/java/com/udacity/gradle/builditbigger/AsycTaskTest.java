package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kamini on 1/4/2016.
 */
public class AsycTaskTest extends AndroidTestCase implements AsyncTaskCompleteListener {

    AsyncJokeTask downloader;
    CountDownLatch signal;
    String joke = "";

    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        downloader = new AsyncJokeTask(this);
    }

    @UiThreadTest
    public void testDownload() throws InterruptedException {
        downloader.downloadJoke();
        signal.await(30, TimeUnit.SECONDS);
        Log.e(downloader.LOG_TAG,joke);
        assertTrue("Valid joke is returned", joke != null);
    }

    @Override
    public void onTaskComplete(Object result) {

        joke = (String)result;
        Log.e(downloader.LOG_TAG,joke);
        signal.countDown();
    }

    @Override
    public void onTaskBefore() {

    }

    @Override
    public void onAsyncExceptionRaised(Exception e) {

    }
}
