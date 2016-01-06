package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Created by Kamini on 12/30/2015.
 */
public class AsyncJokeTask {

    private static final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static final boolean DEBUG = true; // Set this to false to disable logs.
    private static JokeApi myApiService = null;

    private final AsyncTaskCompleteListener<String> mlistener;
    private Exception mException = null;

    AsyncJokeTask(AsyncTaskCompleteListener<String> listener) {
        this.mlistener = listener;
    }

    public void downloadJoke()
    {
        new EndpointsAsyncTask().execute();
    }
    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            if (myApiService == null) {  // Only do this once
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - 10.0.3.2 is localhost's IP address in Genymotion emulator
                        // - turn off compression when running against local devappserver
                      //  .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                       //.setRootUrl("https://android-app-backend.appspot.com/_ah/api/")
                       .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                      // .setRootUrl("http://192.168.2.115:8080/_ah/api/")
                       // .setRootUrl("http://192.168.45.2.:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }


            try {
                //For debugging
                //Thread.sleep(2000);
                return myApiService.getJoke().execute().getData();
            } catch (Exception e) {
                mException = e;
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Log.d(LOG_TAG, "onPostExecute");
            if (mException != null) {
                mlistener.onAsyncExceptionRaised(mException);

            } else {
                mlistener.onTaskComplete(result);
            }
        }

        @Override
        protected void onPreExecute() {
            mlistener.onTaskBefore();

        }

    }
}
