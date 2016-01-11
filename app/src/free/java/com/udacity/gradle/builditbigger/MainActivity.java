package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.JokeTeller;
import com.example.android.androiddisplayjokelibrary.DisplayJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends ActionBarActivity {

    InterstitialAd mInterstitialAd;
    Intent intent ;
  //private A mEndpointsAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                if(intent!=null){

                    startActivity(intent);
                }
            }
        });

        requestNewInterstitial();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }





    public void tellJoke(View view) {

      new AsyncJokeTask(new AsyncTaskCompleteListener<String>() {
          @Override
          public void onTaskComplete(String result) {
              intent = new Intent(getApplicationContext(), DisplayJokeActivity.class);
            //  Intent intent = new Intent(getApplicationContext(), DisplayJokeActivity.class);
              intent.putExtra(DisplayJokeActivity.JOKE_KEY, result);
             // startActivity(intent);
              if (mInterstitialAd.isLoaded()) {
                  mInterstitialAd.show();
              }
          }

          @Override
          public void onAsyncExceptionRaised(Exception e) {
              Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onTaskBefore() {

          }
      }).downloadJoke();

    }


    }


