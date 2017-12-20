package com.example.lurenman.androidtouchgestures.activity;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.lurenman.androidtouchgestures.R;
import com.example.lurenman.androidtouchgestures.views.GestureDetectorView;

/**
 * @author: baiyang.
 * Created on 2017/12/14.
 * 参考 好文章
 * http://blog.csdn.net/harvic880925/article/details/39520901
 * 触发顺序：
 * 点击一下非常快的（不滑动）Touchup：
 * onDown->onSingleTapUp->onSingleTapConfirmed
 * 点击一下稍微慢点的（不滑动）Touchup：
 * onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
 * <p>
 * 滑屏：手指触动屏幕后，稍微滑动后立即松开
 * onDown-----》onScroll----》onScroll----》onScroll----》………----->onFling
 * 拖动
 * onDown------》onScroll----》onScroll------》onFiling
 * 可见，无论是滑屏，还是拖动，影响的只是中间OnScroll触发的数量多少而已，最终都会触发onFling事件！
 */

public class GestureDetectorActivity extends BaseActivity implements View.OnTouchListener {
    private static final String TAG = "GestureDetectorActivity";
    private GestureDetector mGestureDetector = null;
    private TextView tv_gesture;
    private TextView tv_doubleTap;
    private GestureDetectorView gd_GestureDetectorView;


    @Override
    protected String getActionBarTitle() {
        return "GestureDetector";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_gesturedetector);
        tv_gesture = (TextView) findViewById(R.id.tv_gesture);
        tv_doubleTap = (TextView) findViewById(R.id.tv_doubleTap);
        gd_GestureDetectorView = (GestureDetectorView) findViewById(R.id.gd_GestureDetectorView);
        gd_GestureDetectorView.setOnTouchListener(this);
        //下面这些不设置scroll不会相应，单机双击事件不会响应
        gd_GestureDetectorView.setFocusable(true);
        gd_GestureDetectorView.setClickable(true);
        gd_GestureDetectorView.setLongClickable(true);
        // 构造GestureDetector对象，传入监听器对象
        mGestureDetector = new GestureDetector(this, mOnGestureListener);
        // 传入双击监听器对象
        mGestureDetector.setOnDoubleTapListener(mOnDoubleTapListener);

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }
    /*
      * 在onTouch()方法中，我们调用GestureDetector的onTouchEvent()方法，将捕捉到的MotionEvent交给GestureDetector
      * 来分析是否有合适的callback函数来处理用户的手势
      */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 在onTouchEvent方法中将事件传递给手势检测对象，否则手势监听对象中的回调函数是不会被调用的
      //  mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener() {
        /**
         * 用户按下屏幕就会触发
         * @param e
         * @return
         */
        @Override
        public boolean onDown(MotionEvent e) {
            Log.e(TAG, "onDown: " + e.toString());
            tv_gesture.setText("onDown");
            return false;
        }

        /**
         * 如果是按下的时间超过瞬间，而且在按下的时候没有松开或者是拖动的，那么onShowPress就会执行
         * @param e
         */
        @Override
        public void onShowPress(MotionEvent e) {
            Log.e(TAG, "onShowPress: " + e.toString());
            tv_gesture.setText("onShowPress");
        }

        /**
         * 从名子也可以看出,一次单独的轻击抬起操作,也就是轻击一下屏幕，立刻抬起来，才会有这个触发，当然,如果除了Down以外还有其它操作,
         * 那就不再算是Single操作了,所以也就不会触发这个事件
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e(TAG, "onSingleTapUp: " + e.toString());
            tv_gesture.setText("onSingleTapUp");
            return false;
        }

        /**
         * 在屏幕上拖动事件。无论是用手拖动view，或者是以抛的动作滚动，都会多次触发,这个方法在ACTION_MOVE动作发生时就会触发
         * @param e1
         * @param e2
         * @param distanceX
         * @param distanceY
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG, "onScroll: " + e1.toString() + ", " + e2.toString());
            Log.e(TAG, "onScroll disX:" + distanceX);
            Log.e(TAG, "onScroll disY:" + distanceY);
            tv_gesture.setText("onScroll");
            return false;
        }

        /**
         * 长按触摸屏，超过一定时长，就会触发这个事件
         * 触发顺序：onDown->onShowPress->onLongPress
         * @param e
         */
        @Override
        public void onLongPress(MotionEvent e) {
            Log.e(TAG, "onLongPress: " + e.toString());
            tv_gesture.setText("onLongPress");
        }

        /**
         * 滑屏，用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
         * @param e1 第1个ACTION_DOWN MotionEvent
         * @param e2 最后一个ACTION_MOVE MotionEvent
         * @param velocityX X轴上的移动速度，像素/秒
         * @param velocityY 轴上的移动速度，像素/秒
         * @return
         */
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e(TAG, "onFling: " + e1.toString() + ", " + e2.toString());
            tv_gesture.setText("onFling ");
            return false;
        }
    };

    private GestureDetector.OnDoubleTapListener mOnDoubleTapListener = new GestureDetector.OnDoubleTapListener() {
        /**
         * 二者的区别是：onSingleTapUp，只要手抬起就会执行，而对于onSingleTapConfirmed来说，
         * 如果双击的话，则onSingleTapConfirmed不会执行。
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.e(TAG, "onSingleTapConfirmed: " + e.toString());
            tv_doubleTap.setText("onSingleTapConfirmed");
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.e(TAG, "onDoubleTap: " + e.toString());
            tv_doubleTap.setText("onDoubleTap");
            return false;
        }

        /**
         * onDoubleTap(MotionEvent e)：双击事件
         * onDoubleTapEvent(MotionEvent e)：双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件
         * 其次在触发OnDoubleTap以后，就开始触发onDoubleTapEvent了，onDoubleTapEvent后面的数字代表了当前的事件，0指ACTION_DOWN，1指ACTION_UP，2 指ACTION_MOVE
         * @param e
         * @return
         */
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.e(TAG, "onDoubleTapEvent: " + e.toString());
            tv_doubleTap.setText("onDoubleTapEvent");
            return false;
        }
    };
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener=new GestureDetector.SimpleOnGestureListener()
    {
       //这里面想重新啥就啥

    };

}
