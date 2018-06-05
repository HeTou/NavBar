package com.zft.navbarlib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fentao.zhong on 2017/5/16.
 * 导航控件
 * <p>
 * 需求：
 * 1、 可以随意设置分割线
 * 2、 可以自己设置动画
 */
public class FtTabLayout extends LinearLayout implements View.OnClickListener {
    //当前选择的坐标索引
    private int mCurrentIndex = -2;     //默认为未选择的状态
    List<View> navList = new ArrayList<>();        //控件列表

    public FtTabLayout(Context context) {
        super(context);
        init(context);
    }

    public FtTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FtTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }


    /***
     *
     * xml初始化完成
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        navList.clear();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView instanceof IFtTab) {
                childView.setOnClickListener(this);     //给所有子控件设置参数
                navList.add(childView);
            }
        }
    }

    /***
     * 处理navigation 点击事件
     * @param v 点击的控件
     * @param index 导航的索引
     */
    private void clickNavigation(View v, int index) {
        if (mCurrentIndex == index) {
            if (onAgainClickListener != null) {
                onAgainClickListener.onAgainClicked(v, index);
            }
        } else {
            mCurrentIndex = index;
            updateUI(v, index);
            if (onSelectedChangeListener != null) {
//               TODO 更新UI
                onSelectedChangeListener.onSelectedChange(v, index);

            }
        }
    }

    /***
     * 更新
     * @param v
     * @param index
     */
    private void updateUI(View v, int index) {
        for (int i = 0; i < navList.size(); i++) {
            IFtTab ftTab = (IFtTab) navList.get(i);
            if (i == index) {
                ftTab.selected();
                ftTab.anim();
            } else {
                ftTab.unselected();
                ftTab.endanim();
            }
        }
    }

    public void setCurrentIndex(int index) {
        View view = navList.get(index);
        clickNavigation(view, index);
    }


    /***
     *
     *
     *
     *
     *  以下是控件自己的回调函数
     */
    private OnSelectedChangeListener onSelectedChangeListener;
    private OnAgainClickListener onAgainClickListener;

    public interface OnSelectedChangeListener {
        void onSelectedChange(View view, int index);
    }

    public interface OnAgainClickListener {
        void onAgainClicked(View view, int index);
    }

    public void setOnSelectedChangeListener(OnSelectedChangeListener onSelectedChangeListener) {
        this.onSelectedChangeListener = onSelectedChangeListener;
    }

    public void setOnAgainClickListener(OnAgainClickListener onAgainClickListener) {
        this.onAgainClickListener = onAgainClickListener;
    }


    @Override
    public void onClick(View v) {
        for (int i = 0; i < navList.size(); i++) {
            View view = navList.get(i);
//          遍历确认点击的是哪一个View的索引
            if (v == view) {
                clickNavigation(v, i);
                break;
            }
        }
    }
}
