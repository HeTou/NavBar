package com.zft.navbarlib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author:zft
 * date:2017/11/1 0001.
 */

public class FtTab extends LinearLayout implements IFtTab {

    public final int rela1 = Animation.RELATIVE_TO_SELF;
    public final int rela2 = Animation.RELATIVE_TO_PARENT;

    private int tabImgId;                      //图片资源id
    private String tabName;                 //tab文本
    private int tabImgWidth = LayoutParams.WRAP_CONTENT;                   //图片宽度
    private int tabImgHeight = LayoutParams.WRAP_CONTENT;                  //图片高度
    private int tabNameTextSize = dp2px(14);            //tab文本大小
    private ColorStateList tabNameTextColor;     //tab文本颜色
    private int tabImg2TextSpace;     //图片到文本的间距
    private int tabAnimType = 0;
    private TextView textView;
    private ImageView imageView;

    public FtTab(Context context) {
        super(context);
        init();
    }

    public FtTab(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FtTab(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray td = context.obtainStyledAttributes(attrs, R.styleable.FtTab, defStyleAttr, 0);
        tabImgId = td.getResourceId(R.styleable.FtTab_tab_img_src, 0);
        tabImgWidth = td.getDimensionPixelSize(R.styleable.FtTab_tab_img_width, tabImgWidth);
        tabImgHeight = td.getDimensionPixelSize(R.styleable.FtTab_tab_img_height, tabImgHeight);
        tabName = td.getString(R.styleable.FtTab_tab_name);
        tabNameTextSize = td.getDimensionPixelSize(R.styleable.FtTab_tab_text_size, tabNameTextSize);
        tabNameTextColor = td.getColorStateList(R.styleable.FtTab_tab_text_color);
        tabImg2TextSpace = td.getDimensionPixelOffset(R.styleable.FtTab_tab_img2text_space, tabImg2TextSpace);
        tabAnimType = td.getInt(R.styleable.FtTab_tab_anim_type, tabAnimType);
        td.recycle();
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setOrientation(LinearLayout.VERTICAL);
        imageView = new ImageView(getContext());
        textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabNameTextSize);
        textView.setTextColor(tabNameTextColor != null ? tabNameTextColor : ColorStateList.valueOf(0XFF000000));
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = dp2px(tabImg2TextSpace);
        addViewInLayout(imageView, 0, new LinearLayout.LayoutParams(tabImgWidth, tabImgHeight));
        addViewInLayout(textView, 1, lp);

        if (tabImgId == 0) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(tabImgId);
        }
        if (!TextUtils.isEmpty(tabName)) {
            textView.setText(tabName);
        }
        requestLayout();
    }

    @Override
    public void anim() {
        switch (tabAnimType) {
            case 1:     //trans
            {
                TranslateAnimation animation = new TranslateAnimation(rela1, 0, rela1, 0, rela1, -0.2f, rela1, 0);
                imageView.setAnimation(animation);
                animation.setDuration(500);
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(animation);
            }
            break;
            case 2:     //scale
            {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.3f, 1, 0.3f, 1, rela1, 0.5f, rela1, 0.5f);
                scaleAnimation.setDuration(500);
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(scaleAnimation);
            }
            break;
            case 3:     //rotate
            {
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(getContext(), 0f, 360f, imageView.getWidth() / 2.0f, imageView.getHeight() / 2.0f, 1f, false);
                rotateAnimation.setDuration(500);
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(rotateAnimation);
            }
            break;
            case 4:     //alph
            {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.4f,1f);
                alphaAnimation.setDuration(500);
                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(alphaAnimation);
            }
                break;
        }
    }

    @Override
    public void endanim() {

    }

    @Override
    public void selected() {
        this.setSelected(true);
        imageView.setSelected(true);
        textView.setSelected(true);
    }

    @Override
    public void unselected() {
        this.setSelected(false);
        imageView.setSelected(false);
        textView.setSelected(false);
    }


    private int dp2px(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
