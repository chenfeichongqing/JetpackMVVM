package com.github.chenfeichongqing.mvvmlib.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.databinding.DataBindingUtil;

import com.github.chenfeichongqing.mvvmlib.R;
import com.github.chenfeichongqing.mvvmlib.databinding.BaseLibLayoutNavSearchBarBinding;
import com.github.chenfeichongqing.mvvmlib.utilcode.util.KeyboardUtils;

import java.util.List;

/**
 * 统一样式的搜索框，bimfoo产品线组件统一
 * 1.执行搜索调用为键盘上的【搜索按钮】，【取消】按钮
 * 2.输入文字不再实时反馈
 * 3.如需要实时反馈调用setNavBarEditListener
 * 4.默认1，2行为请调用setNavBarActionListenr
 * 5.设置提示内容setHint
 * 6.如果根布局没有voice_root记得创建一个FrameLayout。 参见问题、质检、安检、报告、劳务。都是这样做的
 */
public class SaasNavSearchBar extends LinearLayout {
    private NavBarEditListener navBarEditListener;
    private NavBarActionListener navBarActionListener;
    private OnFocusChangeListener focusChangeListener;
    private Activity activity;

    public SaasNavSearchBar(Context context) {
        super(context);
        init(context);

    }

    public SaasNavSearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public SaasNavSearchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public SaasNavSearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private BaseLibLayoutNavSearchBarBinding databinding;

    private void init(Context context) {
        databinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.base_lib_layout_nav_search_bar, this, false);
        addView(databinding.getRoot());
        databinding.navEdit.setOnFocusChangeListener((view, b) ->
        {
            databinding.txtCancel.setVisibility(b ? View.VISIBLE : View.GONE);
            if (focusChangeListener != null) {
                focusChangeListener.onFocusChange(view, b);
            }
        });
        databinding.navEdit.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {//搜索按键action
                KeyboardUtils.hideSoftInput(textView);
                if (navBarActionListener != null) {
                    navBarActionListener.enter(databinding.navEdit.getText().toString());
                    return true;
                }
            }
            return false;
        });

        databinding.navEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (navBarEditListener != null) {
                    databinding.imgClean.setVisibility(editable != null ? ((editable.toString().length() > 0 ? View.VISIBLE : View.GONE)) : View.GONE);
                    navBarEditListener.searchText(editable != null ? editable.toString() : "");
                }
            }
        });

        databinding.txtCancel.setOnClickListener(v -> {
            databinding.navEdit.setText("");
            databinding.navEdit.clearFocus();
            KeyboardUtils.hideSoftInput(v);
            if (navBarActionListener != null) {
                navBarActionListener.enter(databinding.navEdit.getText().toString());
            }
        });

        databinding.imgClean.setOnClickListener(v -> databinding.navEdit.setText(""));
    }


    public final void setHint(CharSequence hint) {
        databinding.navEdit.setHint(hint);
    }

    public final void setHint(int resid) {
        databinding.navEdit.setHint(resid);
    }

    public void setNavBarEditListener(NavBarEditListener navBarEditListener) {
        this.navBarEditListener = navBarEditListener;
    }

    public void setNavBarActionListenr(NavBarActionListener navBarActionListenr) {
        this.navBarActionListener = navBarActionListenr;
    }

    public void setFocusChangeListener(OnFocusChangeListener focusChangeListener) {
        this.focusChangeListener = focusChangeListener;
    }

    public void clean() {
        databinding.navEdit.setText("");
        databinding.navEdit.clearFocus();
        KeyboardUtils.hideSoftInput(this);
    }

    public interface NavBarEditListener {
        void searchText(String text);
    }

    public interface NavBarActionListener {
        void enter(String text);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }


}
