package com.benny.technique.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.benny.technique.utils.LogUtils;

/**
 * 描述：
 * Created by Benny on 2017/2/21.
 * PackageName com.benny.technique.base
 */

public class BaseActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate()");
    }

}
