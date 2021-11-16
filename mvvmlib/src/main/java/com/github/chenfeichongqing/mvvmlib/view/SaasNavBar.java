package com.github.chenfeichongqing.mvvmlib.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import com.github.chenfeichongqing.mvvmlib.R;
import com.github.chenfeichongqing.mvvmlib.databinding.BaseLibLayoutNavBarBinding;
import com.github.chenfeichongqing.mvvmlib.util.OnSingleClickListener;
import com.github.chenfeichongqing.mvvmlib.utilcode.util.ColorUtils;

/**
 * 全局导航样式
 */
public class SaasNavBar extends FrameLayout {
    public SaasNavBar(Context context) {
        super(context);
        init(context, null);
    }

    public SaasNavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

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

    //private int defaultColor, selectedColor;
    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.base_lib_layout_nav_bar, this, false);
        addView(binding.getRoot());
        binding.backTitle.setOnClickListener(new OnSingleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                if (backListener != null) {
                    backListener.click(view);
                }
            }
        });
        binding.tvRightTitle.setOnClickListener(new OnSingleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                if (submitListener != null) {
                    submitListener.click(view);
                }
            }
        });

    }

    public void setTitle(String str) {
        binding.tvCenterTitle.setText(str);
    }

    /**
     * 是否为自定义字体
     *
     * @param title
     * @param isFonts
     */
    public void setRightTitle(String title, boolean isFonts) {
        if (isFonts) {
            binding.tvRightTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                    "iconfont.ttf"));
            binding.tvRightTitle.setTextSize(24);
        }
        binding.tvRightTitle.setText(title);
    }

    /**
     * 是否为自定义字体
     *
     * @param resId
     * @param isFonts
     */
    public void setRightTitle(int resId, boolean isFonts) {
        if (isFonts) {
            binding.tvRightTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                    "iconfont.ttf"));
            binding.tvRightTitle.setTextSize(24);
            binding.tvRightTitle.setTextColor(ColorUtils.getColor(R.color.base_bar_title_color));
        }
        binding.tvRightTitle.setText(resId);
    }

    public void setRightTextColor(int id) {
        binding.tvRightTitle.setTextColor(id);
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
        binding.tvCenterTitle.setVisibility(i);
    }

    public void hideRight(int i) {
        binding.tvRightTitle.setVisibility(i);
    }

    public void hideLeft(int i) {
        binding.backTitle.setVisibility(i);
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
    public TextView getRightView(){
        return  binding.tvRightTitle;
    }

}

