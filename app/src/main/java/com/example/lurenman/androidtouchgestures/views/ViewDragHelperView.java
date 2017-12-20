package com.example.lurenman.androidtouchgestures.views;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2017/12/19.
 * 参考
 * http://www.cnblogs.com/punkisnotdead/p/4724825.html
 * http://blog.csdn.net/lmj623565791/article/details/46858663 鸿洋
 * 感觉ViewDragHelper就是通过scrool 来控制拖动，从界面上可以看出不同childView
 * 根本不在同一层上，猜测是建立一个铺满父容器的ViewGrop，在这个ViewGrop中实现滚动
 */

public class ViewDragHelperView extends LinearLayout {
    private static final String TAG = "ViewDragHelperView";
    private ViewDragHelper mDragger;
    private DraggerCallBack mCallback;
    private ImageView iv_image;
    private View v_view;
    private Point mAutoBackOriginPos = new Point();

    public ViewDragHelperView(Context context) {
        super(context);
    }

    public ViewDragHelperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCallback = new DraggerCallBack();
        //第二个参数就是滑动灵敏度的意思 可以随意设置
        mDragger = ViewDragHelper.create(this, 1.0f, mCallback);
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public ViewDragHelperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 这个就是xml布局加载好的那个看字面就可以猜出
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv_image = (ImageView) findViewById(R.id.iv_image);
        v_view = findViewById(R.id.v_view);
        //到此，我们已经介绍了Callback中常用的回调方法了，当然还有一些方法没有介绍，接下来我们修改下我们的布局文件，
        // 我们把我们的TextView全部加上clickable=true，意思就是子View可以消耗事件。再次运行，你会发现本来可以拖动的View不动了，
        // （如果有拿Button测试的兄弟应该已经发现这个问题了，我希望你看到这了，而不是已经提问了,哈~）。
        //原因是什么呢？主要是因为，如果子View不消耗事件，那么整个手势（DOWN-MOVE*-UP）都是直接进入onTouchEvent，
        // 在onTouchEvent的DOWN的时候就确定了captureView。如果消耗事件，那么就会先走onInterceptTouchEvent方法，
        // 判断是否可以捕获，而在判断的过程中会去判断另外两个回调的方法：getViewHorizontalDragRange和getViewVerticalDragRange，
        // 只有这两个方法返回大于0的值才能正常的捕获。
        iv_image.setClickable(true);
        v_view.setClickable(true);
    }

    /**
     * 这个地方实际上left就代表 你将要移动到的位置的坐标。返回值就是最终确定的移动的位置。
     * 我们要让view滑动的范围在我们的layout之内
     * 实际上就是判断如果这个坐标在layout之内 那我们就返回这个坐标值。
     * 如果这个坐标在layout的边界处 那我们就只能返回边界的坐标给他。不能让他超出这个范围
     * 除此之外就是如果你的layout设置了padding的话，也可以让子view的活动范围在padding之内的.
     */
    private class DraggerCallBack extends ViewDragHelper.Callback {
        //这个用来控制childView是否可以滑动
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
//            if (child==v_view)
//            {
//                return false;
//            }
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //return super.clampViewPositionHorizontal(child, left, dx);
            // return left;
            //取得左边界的坐标
            final int leftBound = getPaddingLeft();
            //取得右边界的坐标
            final int rightBound = getWidth() - child.getWidth() - leftBound;
            //这个地方的含义就是 如果left的值 在leftbound和rightBound之间 那么就返回left
            //如果left的值 比 leftbound还要小 那么就说明 超过了左边界 那我们只能返回给他左边界的值
            //如果left的值 比rightbound还要大 那么就说明 超过了右边界，那我们只能返回给他右边界的值
            return Math.min(Math.max(left, leftBound), rightBound);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            // return super.clampViewPositionVertical(child, top, dy);
            //return top;
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - child.getHeight() - topBound;
            return Math.min(Math.max(top, topBound), bottomBound);
        }

        //在边界拖动时回调  这块边缘我调用没看出啥效果  wait。。。。
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragger.captureChildView(v_view, pointerId);
            Log.e(TAG, "onEdgeDragStarted: -------------");
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            Log.e(TAG, "onEdgeTouched: -------------");
        }

        //手指释放的时候回调
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            // super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == iv_image) {
                mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                invalidate();
            }
        }
        //这块就是child设置Clickable消耗了事件，走->onInterceptTouchEvent->mDragger.shouldInterceptTouchEvent(ev)
        //->在到就是下面这两个方法，很好理解
        @Override
        public int getViewHorizontalDragRange(View child)
        {
            return getMeasuredWidth()-child.getMeasuredWidth();
        }

        @Override
        public int getViewVerticalDragRange(View child)
        {
            return getMeasuredHeight()-child.getMeasuredHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //决定是否拦截当前事件
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理事件
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = iv_image.getLeft();
        mAutoBackOriginPos.y = iv_image.getTop();
    }
}
