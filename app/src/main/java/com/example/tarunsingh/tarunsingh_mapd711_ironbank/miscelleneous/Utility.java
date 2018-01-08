package com.example.tarunsingh.tarunsingh_mapd711_ironbank.miscelleneous;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.view.View;

import com.example.tarunsingh.tarunsingh_mapd711_ironbank.R;

/**
 * Created by tarunsingh on 2018-01-07.
 */

public class Utility {

//    private View mProgressView;

    public static void showProgress(final boolean show, Activity context) {
        int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
        final View mProgressView = context.findViewById(R.id.progress_spinner);

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

}
