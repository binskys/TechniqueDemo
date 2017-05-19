package com.benny.technique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.benny.technique.adapter.ProjectAdapter;
import com.benny.technique.app.DataBindingActivity;
import com.benny.technique.app.GreenDaoActivity;
import com.benny.technique.app.WebBaseActivity;
import com.benny.technique.model.ProjectModel;
import com.benny.technique.utils.AnimationUtil;
import com.benny.technique.utils.ToastUtils;
import com.benny.technique.view.RefreshLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MainActivity extends AppCompatActivity {
    private static String title = "测试";
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.lv_project)
    ListView lvProject;
    @BindView(R.id.swipeRefresh)
    RefreshLayout swipeRefresh;
    List<ProjectModel> list = new ArrayList<>();
    private ProjectAdapter adapter;

    private void PicassoImage() {
        Picasso.with(this).load("http://f.hiphotos.baidu.com/image/pic/item/b151f8198618367a9f738e022a738bd4b21ce573.jpg")
                .transform(new CropCircleTransformation())//CropTransformation, CropCircleTransformation, CropSquareTransformation
                //  .transform(new RoundedCornersTransformation(20, 20))//RoundedCornersTransformation
                .into(ivLogo);
        AnimationView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PicassoImage();
        if (list != null) {
            list.clear();
        }
        addData();

    }

    private void addData() {
        list.add(new ProjectModel("Butterknife框架", 01));
        list.add(new ProjectModel("GreenDao框架", 02));
        list.add(new ProjectModel("Picasso图片框架", 03));
        list.add(new ProjectModel("微信WebView进度条", 04));
        list.add(new ProjectModel("DataBinding基础使用 ", 05));
        list.add(new ProjectModel("Wheel联动 ", 06));
        list.add(new ProjectModel("语音录制 ", 07));
        showProject();
    }

    private void showProject() {
        adapter = new ProjectAdapter(MainActivity.this, list);
        lvProject.setAdapter(adapter);
// 设置下拉刷新时的颜色值,颜色值需要定义在xml中
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimaryDark));
        swipeRefresh.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        addData();
                        adapter.notifyDataSetChanged();
                        // 更新完后调用该方法结束刷新
                        swipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        swipeRefresh.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                swipeRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addData();
                        adapter.notifyDataSetChanged();
                        // 更新完后调用该方法结束刷新
                        swipeRefresh.setLoading(false);
                    }
                }, 2000);
            }
        });
        lvProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        ToastUtils.showShort(MainActivity.this, "ButterKnife");
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                        break;
                    case 2:
                        ToastUtils.showShort(MainActivity.this, "顶部的头像就是");
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, WebBaseActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, DataBindingActivity.class));
                        break;

                    default:
                        ToastUtils.showShort(MainActivity.this, "没点击事件");
                        break;
                }
            }
        });

    }


    private void AnimationView() {
        ivLogo.startAnimation(AnimationUtil.getRotateAnimation(0, 720, 10, 50, 5, 50, 5000, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                title = "测试";

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        }));
    }

}
