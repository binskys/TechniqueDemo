package com.benny.technique.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：所有Adapter都可以直接继承AppAdapter
 * Created by benny on 2016/5/7.
 * ProjectName：mlzMall.
 */
public class AppAdapter<T> extends BaseAdapter {
    public  LayoutInflater mInflater;
    public Context context;
    public List<T> list = new ArrayList<>();
    public int layoutId;

    public AppAdapter(Context context, List<T> list, int layoutId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
        mInflater= LayoutInflater.from(context);
    }

    public AppAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
        mInflater= LayoutInflater.from(context);
    }

    public AppAdapter(Context context) {
        this.context = context;
        mInflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
