package com.benny.technique.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.benny.technique.R;
import com.benny.technique.base.BaseActivity;
import com.benny.technique.databinding.ActWheelLayoutBinding;

/**
 * 描述：
 * Created by Benny on 2017/3/6.
 * PackageName com.benny.technique.app
 */

public class WheelActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActWheelLayoutBinding binding=DataBindingUtil.setContentView(this, R.layout.act_wheel_layout);


    }
}
