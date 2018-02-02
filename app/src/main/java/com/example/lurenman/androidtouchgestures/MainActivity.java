package com.example.lurenman.androidtouchgestures;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.androidtouchgestures.activity.GestureDetectorActivity;
import com.example.lurenman.androidtouchgestures.activity.MultiTouchActivity;
import com.example.lurenman.androidtouchgestures.activity.ScaleGestureDetectorActivity;
import com.example.lurenman.androidtouchgestures.activity.ScrollMainActivity;
import com.example.lurenman.androidtouchgestures.activity.VelocityTrackerActivity;
import com.example.lurenman.androidtouchgestures.activity.ViewDragHelperActivity;

public class MainActivity extends AppCompatActivity {

    private Button bt_GestureDetector;
    private Button bt_VelocityTracker;
    private Button bt_MultiTouch;
    private Button bt_ViewDragHelper;
    private Button bt_Scroll;
    private Button bt_ScaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    private void initViews() {
        bt_GestureDetector = (Button) findViewById(R.id.bt_GestureDetector);
        bt_ScaleGestureDetector = (Button) findViewById(R.id.bt_ScaleGestureDetector);
        bt_VelocityTracker = (Button) findViewById(R.id.bt_VelocityTracker);
        bt_MultiTouch = (Button) findViewById(R.id.bt_MultiTouch);
        bt_ViewDragHelper = (Button) findViewById(R.id.bt_ViewDragHelper);
        bt_Scroll = (Button) findViewById(R.id.bt_Scroll);
    }

    private void initEvents() {
        bt_GestureDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GestureDetectorActivity.class);
                startActivity(intent);
            }
        });
        bt_ScaleGestureDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScaleGestureDetectorActivity.class);
                startActivity(intent);
            }
        });
        bt_VelocityTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VelocityTrackerActivity.class);
                startActivity(intent);
            }
        });
        bt_MultiTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MultiTouchActivity.class);
                startActivity(intent);
            }
        });
        bt_ViewDragHelper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewDragHelperActivity.class);
                startActivity(intent);
            }
        });
        bt_Scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollMainActivity.class);
                startActivity(intent);
            }
        });

    }
}
