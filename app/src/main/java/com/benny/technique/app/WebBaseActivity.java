package com.benny.technique.app;

import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.benny.technique.R;
import com.benny.technique.base.BaseActivity;
import com.benny.technique.utils.IsNetUtils;
import com.benny.technique.view.progressbar.HProgressBarLoading;

public class WebBaseActivity extends BaseActivity {

    WebView webView;
    HProgressBarLoading mTopProgress;
    TextView mTvCenterBadnet;
    RelativeLayout mActivityBasicWeb;
    private boolean isContinue = false;
    public static final String LoadURL = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_web);
        findView();

        initWebSetting();
    }

    private void initWebSetting() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(false);
        //正常网络流程
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //如果没有网络直接跳出方法
                if (!IsNetUtils.isNetworkAvailable(WebBaseActivity.this)) {
                    return;
                }
                //如果进度条隐藏则让它显示
                if (View.INVISIBLE == mTopProgress.getVisibility()) {
                    mTopProgress.setVisibility(View.VISIBLE);
                }
                //大于80的进度的时候,放慢速度加载,否则交给自己加载
                if (newProgress >= 80) {
                    //拦截webView自己的处理方式
                    if (isContinue) {
                        return;
                    }
                    mTopProgress.setCurProgress(100, 3000, new HProgressBarLoading.OnEndListener() {
                        @Override
                        public void onEnd() {
                            finishOperation(true);
                            isContinue = false;
                        }
                    });

                    isContinue = true;
                } else {
                    mTopProgress.setNormalProgress(newProgress);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            //https的处理方式
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            //错误页面的逻辑处理
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                errorOperation();
            }
        });
        //开始加载
        webView.loadUrl(LoadURL);
    }


    /**
     * 错误的时候进行的操作
     */
    private void errorOperation() {
        //隐藏webview
        webView.setVisibility(View.INVISIBLE);

        if (View.INVISIBLE == mTopProgress.getVisibility()) {
            mTopProgress.setVisibility(View.VISIBLE);
        }
        //3.5s 加载 0->80 进度的加载 为了实现,特意调节长了事件
        mTopProgress.setCurProgress(80, 3500, new HProgressBarLoading.OnEndListener() {
            @Override
            public void onEnd() {
                //3.5s 加载 80->100 进度的加载
                mTopProgress.setCurProgress(100, 3500, new HProgressBarLoading.OnEndListener() {
                    @Override
                    public void onEnd() {
                        finishOperation(false);
                    }
                });
            }
        });
    }

    /**
     * 结束进行的操作
     */
    private void finishOperation(boolean flag) {
        //最后加载设置100进度
        mTopProgress.setNormalProgress(100);
        //显示网络异常布局
        mTvCenterBadnet.setVisibility(flag ? View.INVISIBLE : View.VISIBLE);
        //点击重新连接网络
        mTvCenterBadnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvCenterBadnet.setVisibility(View.INVISIBLE);
                //重新加载网页
                webView.reload();
            }
        });
        hideProgressWithAnim();
    }

    /**
     * 隐藏加载对话框
     */
    private void hideProgressWithAnim() {
        AnimationSet animation = getDismissAnim(WebBaseActivity.this);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTopProgress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mTopProgress.startAnimation(animation);
    }

    /**
     * 获取消失的动画
     *
     * @param context
     * @return
     */
    private AnimationSet getDismissAnim(Context context) {
        AnimationSet dismiss = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(1000);
        dismiss.addAnimation(alpha);
        return dismiss;
    }

    private void findView() {
        webView = (WebView) findViewById(R.id.webView);
        mTopProgress = (HProgressBarLoading) findViewById(R.id.top_progress);
        mTvCenterBadnet = (TextView) findViewById(R.id.tv_center_badnet);
        mActivityBasicWeb = (RelativeLayout) findViewById(R.id.activity_basic_web);
    }
}
