package com.benny.technique.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ListView;

import com.benny.technique.R;
import com.benny.technique.adapter.UserAdapter;
import com.benny.technique.base.BaseActivity;
import com.benny.technique.greendao.User;
import com.benny.technique.greendao.UserDao;
import com.benny.technique.utils.ToastUtils;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 描述：
 * Created by Benny on 2017/2/21.
 * PackageName com.benny.technique.greendao
 */

public class GreenDaoActivity extends BaseActivity {
    @BindView(R.id.lv_userList)
    ListView listView;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_age)
    EditText etAge;
    private UserDao userDao;
    private UserAdapter adapter;
    List<User> list = new ArrayList<>();

    @OnClick(R.id.btn_add)
    void addUser() {
        String user = etUser.getText().toString();
        String age = etAge.getText().toString();
        if (user.isEmpty()) {
            ToastUtils.showShort(context, "姓名为空");
            return;
        }
        if (age.isEmpty()) {
            ToastUtils.showShort(context, "年龄为空");
            return;
        }
        User userMD = new User(null, user, Integer.valueOf(age));
        userDao.insert(userMD);

        queryAll();

    }
    @OnClick(R.id.btn_search)
    void searchUser() {
        //查询一条
        // Query 类代表了一个可以被重复执行的查询
        String name=etUser.getText().toString();
        Query query = userDao.queryBuilder()
                .where(UserDao.Properties.Name.eq(name))
                .orderAsc(UserDao.Properties.Age)
                .build();

//      查询结果以 List 返回
        list = query.list();
        intAdapter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        userDao = daoSession.getUserDao();

        queryAll();
    }


    /**
     * 查询全部
     */
    private void queryAll() {
        // Query 类代表了一个可以被重复执行的查询
        Query<User> query = userDao.queryBuilder()
                .orderAsc(UserDao.Properties.Id)
                .build();
        // 查询结果以 List 返回
        List count = query.list();
        ToastUtils.showShort(context, count.size() + "条数据被查到");

        list = userDao.queryBuilder().list();
intAdapter();


    }

    private void intAdapter() {
        adapter = new UserAdapter(GreenDaoActivity.this, list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetInvalidated();

        adapter.setUserDaoListener(new UserAdapter.UserDaoListener() {
            @Override
            public void del(long id) {
                userDao.deleteByKey(id);
                list.clear();
                queryAll();
            }

            @Override
            public void upData(String name, int age, long id) {
                User userMD = new User(id, "user_updata", Integer.valueOf(age));
                userDao.update(userMD);
                list.clear();
                queryAll();

            }
        });
    }
}
