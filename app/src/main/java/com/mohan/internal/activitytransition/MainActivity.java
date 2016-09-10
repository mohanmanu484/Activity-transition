package com.mohan.internal.activitytransition;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set an exit transition
        requestFeature();
        setContentView(R.layout.activity_main);

        // inside your activity (if you did not enable transitions in your theme)

        // get the element that receives the click event
        final View imgContainerView = findViewById(R.id.img_container);

// get the common element for the transition in this activity
        final View androidRobotView = findViewById(R.id.image_small);

// define a click listener
        imgContainerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                ActivityOptionsCompat options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> pair1 = Pair.create(androidRobotView, androidRobotView.getTransitionName());
                    options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(MainActivity.this, pair1);
                    startActivity(intent, options.toBundle());
                }else {
                    startActivity(intent);
                }
                // start the new activity

            }
        });
    }

    private void requestFeature() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition fade = new Fade();
            fade.setDuration(2000);
            fade.excludeTarget(android.R.id.statusBarBackground, true);
            fade.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setExitTransition(fade);
            getWindow().setEnterTransition(fade);
        }
    }


}
