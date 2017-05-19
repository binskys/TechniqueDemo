package com.benny.technique.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.benny.technique.R;
import com.benny.technique.adapter.base.AppAdapter;
import com.benny.technique.greendao.User;

import java.util.List;

/**
 * 描述：
 * Created by Benny on 2017/2/27.
 * PackageName com.benny.technique.adapter
 */

public class UserAdapter extends AppAdapter<User> {
    UserDaoListener userDaoListener;

    public UserDaoListener getUserDaoListener() {
        return userDaoListener;
    }

    public void setUserDaoListener(UserDaoListener userDaoListener) {
        this.userDaoListener = userDaoListener;
    }

    public UserAdapter(Context context, List<User> list) {
        super(context, list);
    }

    @Override
    public View getView( int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.activity_user_item, null);
            holder.user = (TextView) view.findViewById(R.id.tv_user);
            holder.age = (TextView) view.findViewById(R.id.tv_age);
            holder.upload = (Button) view.findViewById(R.id.btn_upload);
            holder.del = (Button) view.findViewById(R.id.btn_del);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
       final String name=list.get(position).getName();
       final int age=list.get(position).getAge();
        holder.user.setText(name);
        final long id=list.get(position).getId();
        holder.age.setText(id+"");
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDaoListener.del(id);
            }
        });
        holder.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDaoListener.upData(name,age,id);
            }
        });
        return view;
    }

    private class ViewHolder {
        public TextView user;
        public TextView age;
        public Button del;
        public Button upload;
    }

    public interface UserDaoListener {
        void del(long id);

        void upData(String name, int age,long id);
    }

}
