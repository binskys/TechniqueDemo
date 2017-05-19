package com.benny.technique.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.benny.technique.greendao.DaoSession;
import com.benny.technique.greendao.GreenDaoManager;
import com.benny.technique.utils.LogUtils;

/**
 * Created by benny .
 * Time on 2017/3/7 .
 * 描述：
 */

public class BaseFragmentActivity extends FragmentActivity{
    protected DaoSession daoSession;
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate()");
        context=this;
        daoSession= GreenDaoManager.getInstance().getSession();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.i("onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("onDestroy()");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_in);
    }
}
