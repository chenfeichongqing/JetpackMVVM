package com.github.chenfeichongqing.mvvmlib.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import com.github.chenfeichongqing.mvvmlib.R;
import com.github.chenfeichongqing.mvvmlib.databinding.BaseLibLayoutNavBarBinding;
import com.github.chenfeichongqing.mvvmlib.util.OnSingleClickListener;

/**
 * 全局导航样式
 */
public class SaasNavBar extends FrameLayout {

    public SaasNavBar(Context context) {
        this(context,null);
    }

    public SaasNavBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }
    public SaasNavBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SaasNavBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private BaseLibLayoutNavBarBinding binding;
    private BackListener backListener;
    private SubmitListener submitListener;

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.base_lib_layout_nav_bar, this, false);
        addView(binding.getRoot());
        binding.ivNavigateBefore.setOnClickListener(new OnSingleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                if (backListener != null) {
                    backListener.click(view);
                }
            }
        });
        binding.tvRightText.setOnClickListener(new OnSingleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                if (submitListener != null) {
                    submitListener.click(view);
                }
            }
        });

    }

    public void setTitle(String str) {
        binding.tvTitle.setText(str);
    }

    /**
     * 是否为自定义字体
     *
     * @param title
     */
    public void setRightTitle(String title) {
        binding.tvRightText.setText(title);
    }
    /**
     * 是否为自定义字体
     *
     * @param resId
     */
    public void setRightTitle(int resId) {
        binding.tvRightText.setText(resId);
    }

    /**
     * 是否为自定义字体
     *
     * @param resId
     */
    public void setRightImage(int resId) {
        binding.ivShare.setImageResource(resId);
    }

    public void setRightTextColor(int id) {
        binding.tvRightText.setTextColor(id);
    }

    @Override
    public void setBackgroundColor(int color) {
        binding.toolbar.setBackgroundColor(color);
    }

    @Override
    public void setBackground(Drawable background) {
        binding.toolbar.setBackground(background);
    }

    public void hideTitle(int i) {
        binding.tvTitle.setVisibility(i);
    }
    public void hideRightTextView(int i) {
        binding.tvRightText.setVisibility(i);
    }

    public void hideLeft(int i) {
        binding.ivNavigateBefore.setVisibility(i);
    }

    public interface BackListener {
        void click(View view);
    }

    public interface SubmitListener {
        void click(View view);
    }

    public void setBackListener(BackListener backListener) {
        this.backListener = backListener;
    }

    public void setSubmitListener(SubmitListener submitListener) {
        this.submitListener = submitListener;
    }
    public TextView getRightTextView(){
        return  binding.tvRightText;
    }
    public ImageView getRightImageView(){
        return  binding.ivShare;
    }
}

