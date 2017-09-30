package com.hack.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    FloatingActionButton add, message, video, call;
    Animation floatButOpen, floatButClose, floatButRotateForward, floatButRotateBackward;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
        Toast.makeText(Details.this, "value : " + value, Toast.LENGTH_SHORT).show();

        add = (FloatingActionButton) findViewById(R.id.add);
        message = (FloatingActionButton) findViewById(R.id.message);
        video = (FloatingActionButton) findViewById(R.id.video);
        call = (FloatingActionButton) findViewById(R.id.call);

        floatButOpen = AnimationUtils.loadAnimation(this, R.anim.floatbutton_open);
        floatButClose = AnimationUtils.loadAnimation(this, R.anim.floatbutton_close);

        floatButRotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        floatButRotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(Details.this, "Message float clicked", Toast.LENGTH_SHORT).show();
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(Details.this, "Video float clicked", Toast.LENGTH_SHORT).show();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFloatBut();
                Toast.makeText(Details.this, "Call float clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void animateFloatBut() {
        if (isOpen) {
            add.startAnimation(floatButRotateBackward);
            message.startAnimation(floatButClose);
            video.startAnimation(floatButClose);
            call.startAnimation(floatButClose);
            message.setClickable(false);
            video.setClickable(false);
            call.setClickable(false);
            isOpen = false;
        }else {
            add.startAnimation(floatButRotateForward);
            message.startAnimation(floatButOpen);
            video.startAnimation(floatButOpen);
            call.startAnimation(floatButOpen);
            message.setClickable(true);
            video.setClickable(true);
            call.setClickable(true);
            isOpen = true;
        }
    }

}
