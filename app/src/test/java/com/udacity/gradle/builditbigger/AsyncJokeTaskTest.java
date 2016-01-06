package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import android.text.TextUtils;
import android.util.Log;

import com.example.android.androiddisplayjokelibrary.DisplayJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.builditbigger.backend.jokeApi.JokeApi;

import junit.framework.TestCase;

import org.junit.Assert.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kamini on 12/30/2015.
 */
public class AsyncJokeTaskTest extends AndroidTestCase {

  //  extends ApplicationTestCase<application> {

    AsyncJokeTask downloader;
    CountDownLatch signal;
   String joke = "";

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }


    @UiThreadTest
    public void testDownload() throws InterruptedException {
        new AsyncJokeTask(new AsyncTaskCompleteListener<String>() {
            @Override
            public void onTaskComplete(String result) {
               joke=result;
            }

            @Override
            public void onAsyncExceptionRaised(Exception e) {

            }

            @Override
            public void onTaskBefore() {
                joke= "";
            }
        }).downloadJoke();
        signal.await();
        assertNotNull(joke);
      //  assertTrue(joke!=null);
       // assertFalse(TextUtils.isEmpty(joke));
    }
}
