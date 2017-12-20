package com.example.lurenman.androidtouchgestures.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2017/12/14.
 * 多点触控
 * 当多个pointer同时触摸屏幕，系统会生成如下事件：
 * <p>
 * ACTION_DOWN—For the first pointer that touches the screen. This starts the gesture. The pointer data for this pointer is always at index 0 in the MotionEvent.
 * ACTION_POINTER_DOWN—For extra pointers that enter the screen beyond the first. The pointer data for this pointer is at the index returned by getActionIndex().
 * ACTION_MOVE—A change has happened during a press gesture.
 * ACTION_POINTER_UP—Sent when a non-primary pointer goes up.
 * ACTION_UP—Sent when the last pointer leaves the screen.
 */

public class MultiTouchActivity extends BaseActivity {
    private static final String TAG = "MultiTouchActivity";

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "MultiTouch";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_multitouch);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 支持多指触控
        int actionMasked = MotionEventCompat.getActionMasked(event);
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                int pointerId = event.getPointerId(0);
                Log.e(TAG, "ACTION_DOWN: " + pointerId);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // 多手指按下,这块执行的是除第一手指按下的其它手指执行的事件
                int pointerDownIndex = MotionEventCompat.getActionIndex(event);
                int pointerId1 = event.getPointerId(pointerDownIndex);
                Log.e(TAG, "ACTION_POINTER_DOWN Index: " + pointerDownIndex);
                Log.e(TAG, "ACTION_POINTER_DOWN: ID: " + pointerId1);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //这块执行的是除最后一个手指抬起的其它手指的事件
                int actionIndex = MotionEventCompat.getActionIndex(event);
                int pointerId2 = event.getPointerId(actionIndex);
                Log.e(TAG, "ACTION_POINTER_UP Index: "+actionIndex);
                Log.e(TAG, "ACTION_POINTER_UP ID: "+pointerId2);
                break;
            case MotionEvent.ACTION_UP:
                //这个抬起的时候执行最后一个按压在屏幕上的事件
                int actionIndex1 = MotionEventCompat.getActionIndex(event);
                int pointerId3 = event.getPointerId(actionIndex1);
                Log.e(TAG, "ACTION_UP Index: "+actionIndex1);
                Log.e(TAG, "ACTION_UP ID: "+pointerId3);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
