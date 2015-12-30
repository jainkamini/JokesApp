package com.example.android.androiddisplayjokelibrary;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DisplayJokeActivityFragment extends Fragment {

    public DisplayJokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // return inflater.inflate(R.layout.fragment_display_joke, container, false);

        View root = inflater.inflate(R.layout.fragment_display_joke, container, false);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(DisplayJokeActivity.JOKE_KEY);
        TextView jokeTextView = (TextView) root.findViewById(R.id.txtJoke);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }


        return root;
    }
}
