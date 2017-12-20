package com.example.lurenman.androidtouchgestures.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.example.lurenman.androidtouchgestures.R;

/**
 * @author: baiyang.
 * Created on 2017/12/20.
 */

public class ScrollMainActivity extends BaseActivity {

    private Button bt_Scroll;
    private Button bt_Scroller;

    @NonNull
    @Override
    protected String getActionBarTitle() {
        return "ScrollMain";
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_main_scroll);
        bt_Scroll = (Button) findViewById(R.id.bt_Scroll);
        bt_Scroller = (Button) findViewById(R.id.bt_Scroller);

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        bt_Scroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ScrollActivity.class);
                startActivity(intent);
            }
        });
        bt_Scroller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ScrollerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
