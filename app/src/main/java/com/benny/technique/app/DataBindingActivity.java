package com.benny.technique.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.benny.technique.R;
import com.benny.technique.base.BaseActivity;
import com.benny.technique.databinding.ActivityUserInfoBinding;
import com.benny.technique.model.UserInfo;

/**
 * 描述：
 * Created by Benny on 2017/3/6.
 * PackageName com.benny.technique.app
 */

public class DataBindingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserInfoBinding userInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_info);
        UserInfo info = new UserInfo("benny", 18, "男", "18170466691");
        userInfoBinding.setUser(info);

    }
}
