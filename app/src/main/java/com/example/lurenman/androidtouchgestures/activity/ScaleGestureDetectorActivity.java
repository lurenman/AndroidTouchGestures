package com.example.lurenman.androidtouchgestures.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2018/2/2.
 * 参考
 * http://blog.csdn.net/u010410408/article/details/39577399
 */

public class ScaleGestureDetectorActivity extends BaseActivity {
    private static final String TAG = "ScaleGestureDetectorAct";
    private ScaleGestureDetector mScaleGestureDetector = null;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "ScaleGestureDetector";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_scale_gesture);

    }

    @Override
    protected void initVariables() {
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                //获取本次缩放事件的缩放因子(缩放事件以onScale()返回值为基准，一旦该方法返回true，代表本次事件结束，要开启下次事件)。它的返回值是指本次事件中的缩放值，并不是相对于最开始的值
                float scaleFactor = detector.getScaleFactor();
                //返回手势过程中，组成该手势的两个触点的当前距离。返回值以像素为单位的触点距离。
                float currentSpan = detector.getCurrentSpan();
                float currentSpanX = detector.getCurrentSpanX();
                float currentSpanY = detector.getCurrentSpanY();
                //getFocusY(),getFocusX()：返回组成该手势的两个触点的中点在组件上的y,x轴坐标，单位为像素。
                float focusX = detector.getFocusX();
                float focusY = detector.getFocusY();
                //getPreviousSpan()：返回缩放过程中，组成当前缩放手势的两个触点的前一次距离。假设有a,b,c三个手指，某一次a,b组成缩放手势，两者的距离是300；随后一直是b,c组成缩放手势，当c抬起时，b,c的距离时100。此时，ab会组成缩放手势，该值返回的就是300，而不是b,c的100。
                //getPreviousSpanX(),getPreviousSpanY()：同getPreviousSpan()类似。
                float previousSpan = detector.getPreviousSpan();
                float previousSpanX = detector.getPreviousSpanX();
                float previousSpanY = detector.getPreviousSpanY();
                //getEventTime()：获取当前motion事件的时间
                long eventTime = detector.getEventTime();
                // getTimeDelta()：返回上次缩放事件结束时到当前的时间间隔
                long timeDelta = detector.getTimeDelta();

                Log.e(TAG, "onScale: ScaleFactor" + scaleFactor);
                return false;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                //这块要是不返回true onScale不执行
                return true;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScaleGestureDetector.onTouchEvent(event);
    }
}
