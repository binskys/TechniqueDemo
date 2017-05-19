package com.benny.technique.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benny.technique.R;

/**
 * Created by benny .
 * Time on 2017/3/13 .
 * 描述：
 */

public class InputView extends LinearLayout {
    private TextView tvLeft;
    private ImageView ivRight;
    private EditText etContet;
    private View viewDivider;
    private Drawable ivSrc;
    private LinearLayout input_ll;
    private int llBgColor;
    private int tvBgColor;
    private int etBgColor;
    private int ivBgColor;
    private int sizeTv;
    private int sizeEt;
    private int textColor_et;
    private int textColor_tv;
    private int textColorHint_et;
    private int textColorHint_tv;
    private String etTextStr;
    private String tvTextStr;
    private String etTextHint;
    private String tvTextHint;
    private boolean isEt;
    private boolean isIv;
    private boolean isEnabled;
    private boolean isDivider;
    private int gravityTv;
    private int gravityEt;

    public InputView(Context context) {
        super(context);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.InputStyleable);

            etTextStr = array.getString(R.styleable.InputStyleable_text_et);
            tvTextStr = array.getString(R.styleable.InputStyleable_text_tv);
            etTextHint = array.getString(R.styleable.InputStyleable_textHint_et);
            tvTextHint = array.getString(R.styleable.InputStyleable_textHint_tv);

            textColor_et = array.getColor(R.styleable.InputStyleable_textColor_et, Color.parseColor("#000000"));
            textColor_tv = array.getColor(R.styleable.InputStyleable_textColor_tv, Color.parseColor("#000000"));
            textColorHint_et = array.getColor(R.styleable.InputStyleable_textColorHint_et, Color.parseColor("#000000"));
            textColorHint_tv = array.getColor(R.styleable.InputStyleable_textColorHint_tv, Color.parseColor("#00000000"));


            sizeEt = array.getInteger(R.styleable.InputStyleable_size_et, 16);
            sizeTv = array.getInteger(R.styleable.InputStyleable_size_tv, 16);
            gravityTv = array.getInteger(R.styleable.InputStyleable_tv_gravity, Gravity.CENTER|Gravity.RIGHT);
            gravityEt = array.getInteger(R.styleable.InputStyleable_et_gravity, Gravity.CENTER|Gravity.LEFT);

            llBgColor = array.getColor(R.styleable.InputStyleable_background_ll, Color.parseColor("#00000000"));
            tvBgColor = array.getColor(R.styleable.InputStyleable_background_tv, Color.parseColor("#00000000"));
            etBgColor = array.getColor(R.styleable.InputStyleable_background_et, Color.parseColor("#00000000"));
            ivBgColor = array.getColor(R.styleable.InputStyleable_background_iv, Color.parseColor("#00000000"));

            ivSrc = array.getDrawable(R.styleable.InputStyleable_src_iv);

            isIv = array.getBoolean(R.styleable.InputStyleable_visibility_iv, true);
            isEt = array.getBoolean(R.styleable.InputStyleable_visibility_et, true);
            isDivider = array.getBoolean(R.styleable.InputStyleable_isdivider, true);

            isEnabled = array.getBoolean(R.styleable.InputStyleable_enabled, true);
            initInputView(array);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) array.recycle();
        }
        inflate(context, R.layout.activity_editview, this);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        input_ll = (LinearLayout) findViewById(R.id.input_ll);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        etContet = (EditText) findViewById(R.id.et_content);
        viewDivider = findViewById(R.id.divider);
        setEditViewStyle();//设置输入框
        setTextViewStyle();//设置文本款
        setImageStyle();//设置图标
        setBackColor();//设置背景
        setIsEnabled(isEnabled);//设置是否编辑
        setVisibilityIv(isIv);//设置试图是否可见
        setVisibilityEt(isEt);//设置试图是否可见
        isDivider(isDivider);
        setGravityTv();
        setGravityEt();
    }

    private void setGravityEt() {
        etContet.setGravity(gravityEt);
    }

    private void setGravityTv() {
        tvLeft.setGravity(gravityTv);
    }

    public void isDivider(boolean isDivider) {
        viewDivider.setVisibility(isDivider?VISIBLE:GONE);
    }


    public void setIsEnabled(boolean isEnabled) {
        etContet.setEnabled(isEnabled);
        etContet.setFocusable(isEnabled);
        etContet.setFocusableInTouchMode(isEnabled);
    }

    public void setVisibilityIv(boolean isIv) {
        if (isIv) {
            ivRight.setVisibility(VISIBLE);
        } else {
            ivRight.setVisibility(INVISIBLE);
        }

    }

    public void setVisibilityEt(boolean isEt) {
        if (isEt) {
            etContet.setVisibility(VISIBLE);
        } else {
            etContet.setVisibility(INVISIBLE);
        }
    }


    private void setBackColor() {
        if (llBgColor != 0) {
            input_ll.setBackgroundColor(llBgColor);
        }
        if (tvBgColor != 0) {
            tvLeft.setBackgroundColor(tvBgColor);
        }
        if (etBgColor != 0) {
            etContet.setBackgroundColor(etBgColor);
        }
        if (ivBgColor != 0) {
            ivRight.setBackgroundColor(ivBgColor);
        }
    }

    // tvSize tvHintText tvText tvColor tvColorHint
    private void setTextViewStyle() {
        if (sizeTv != 0) tvLeft.setTextSize(sizeTv);
        if (!TextUtils.isEmpty(tvTextStr)) tvLeft.setText(tvTextStr);
        if (!TextUtils.isEmpty(tvTextHint)) tvLeft.setHint(tvTextHint);
        if (textColor_tv != 0) tvLeft.setTextColor(textColor_tv);
        if (textColorHint_tv != 0) tvLeft.setHintTextColor(textColorHint_tv);
    }

    private void setEditViewStyle() {
        if (sizeEt != 0) etContet.setTextSize(sizeEt);
        if (!TextUtils.isEmpty(etTextStr)) {
            etContet.setText(etTextStr);
        } else {
            etContet.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(etTextHint)) etContet.setHint(etTextHint);
        if (textColor_et != 0) etContet.setTextColor(textColor_et);
        if (textColorHint_et != 0) etContet.setHintTextColor(textColorHint_et);
        etContet.setSelection(getString().length());
    }

    private void setImageStyle() {
        if (ivSrc != null) ivRight.setImageDrawable(ivSrc);
        ViewGroup.LayoutParams lp = ivRight.getLayoutParams();
        lp.width = LayoutParams.WRAP_CONTENT;
        lp.height = LayoutParams.WRAP_CONTENT;
        ivRight.setLayoutParams(lp);
    }

    private void initInputView(TypedArray array) {

    }

    public String getString() {
        return etContet.getText().toString();
    }

    public String getEditString() {
        return tvLeft.getText().toString();
    }

}
