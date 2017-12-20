package com.example.lurenman.androidtouchgestures.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2017/12/14.
 * android.view.VelocityTracker主要用跟踪触摸屏事件（flinging事件和其他gestures手势事件）的速率。
 * 用addMovement(MotionEvent)函数将Motion event加入到VelocityTracker类实例中.你可以使用getXVelocity()
 * 或getXVelocity()获得横向和竖向的速率到速率时，但是使用它们之前请先调用computeCurrentVelocity(int)来初始化速率的单位 。
 */

public class VelocityTrackerActivity extends BaseActivity {
    private static final String TAG = "VelocityTrackerActivity";
    private VelocityTracker mVelocityTracker = null;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "VelocityTracker";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_velocitytracker);
    }

    @Override
    protected void initVariables() {
        //不解释
        int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        int scaledMaximumFlingVelocity = ViewConfiguration.get(this).getScaledMaximumFlingVelocity();
        Log.e(TAG, "initVariables scaledTouchSlop: " + scaledTouchSlop);
        Log.e(TAG, "initVariables MaximumFlingVelocity: " + scaledMaximumFlingVelocity);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                //这个两个参数的那个代表最大速度，超过这个速度按这个最大速度处理
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                float yVelocity = mVelocityTracker.getYVelocity();
                Log.e(TAG, "onTouchEvent xVelocity: " + xVelocity);
                Log.e(TAG, "onTouchEvent yVelocity: " + yVelocity);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //mVelocityTracker.recycle();
                //使用完VelocityTracker，必须释放资源
                if (mVelocityTracker != null) {
                    mVelocityTracker.clear();
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                break;
            default:
                break;
        }
        return true;
    }
}
