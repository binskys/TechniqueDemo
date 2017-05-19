package com.benny.technique.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benny.technique.MainActivity;
import com.benny.technique.R;
import com.benny.technique.adapter.base.AppAdapter;
import com.benny.technique.model.ProjectModel;

import java.util.List;

/**
 * Created by benny .
 * Time on 2017/3/2 .
 * 描述：
 */
public class ProjectAdapter extends AppAdapter<ProjectModel> {
    private ViewHolder holder;
    public ProjectAdapter(MainActivity mainActivity, List<ProjectModel> list) {
        super(mainActivity, list);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.activity_mian_item, null);
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvName.setText(list.get(position).getProjectName());
        return view;
    }


    private class ViewHolder {
        public TextView tvName;

    }
}
