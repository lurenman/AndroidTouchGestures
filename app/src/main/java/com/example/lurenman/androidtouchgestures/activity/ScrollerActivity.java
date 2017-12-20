package com.example.lurenman.androidtouchgestures.activity;

import android.support.annotation.NonNull;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2017/12/20.
 */

public class ScrollerActivity extends BaseActivity {
    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "Scroller";
    }

    @Override
    protected void initViews() {
     setContentView(R.layout.activity_scroller);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }
}
