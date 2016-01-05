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

public class MainActivity extends ActionBarActivity {


  //private A mEndpointsAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



    public void tellJoke(View view) {

      new AsyncJokeTask(new AsyncTaskCompleteListener<String>() {
          @Override
          public void onTaskComplete(String result) {
              Intent intent = new Intent(getApplicationContext(), DisplayJokeActivity.class);
              intent.putExtra(DisplayJokeActivity.JOKE_KEY, result);
              startActivity(intent);
          }

          @Override
          public void onAsyncExceptionRaised(Exception e) {

          }

          @Override
          public void onTaskBefore() {

          }
      }).downloadJoke();

    }


    }


