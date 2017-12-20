package com.example.lurenman.androidtouchgestures.activity;

import android.support.annotation.NonNull;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2017/12/20.
 * 从测试结果来看真的是滚动的是View的内容
 */

public class ScrollActivity extends BaseActivity {
    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "Scroll";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_scroll);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }
}
