package com.example.lurenman.androidtouchgestures.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: baiyang.
 * Created on 2017/12/20.
 * 原文
 * http://blog.csdn.net/javazejian/article/details/50549962
 */

public class ScrollByDragView extends View {
    private int lastX;
    private int lastY;
    public ScrollByDragView(Context context) {
        super(context);
        initView();
    }

    public ScrollByDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScrollByDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        setBackgroundColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
                default:
                    break;
        }
        return true;
    }
}
