package com.zft.navbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zft.navbar.fragment.Fragment1;
import com.zft.navbar.fragment.Fragment2;
import com.zft.navbar.fragment.Fragment3;
import com.zft.navbar.fragment.Fragment4;
import com.zft.navbar.fragment.Fragment5;
import com.zft.navbarlib.FtTabLayout;

public class MainActivity extends AppCompatActivity implements FtTabLayout.OnSelectedChangeListener, FtTabLayout.OnAgainClickListener {

    private FtTabLayout mTab1;
    private FtTabLayout mTab2;
    private FtTabLayout mTab3;
    private Fragment mCurFragment;
    private Fragment Fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private Fragment fragment4;
    private Fragment fragment5;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTab1 = (FtTabLayout) findViewById(R.id.tab1);
        mTab2 = (FtTabLayout) findViewById(R.id.tab2);
        mTab3 = (FtTabLayout) findViewById(R.id.tab3);
        mTab1.setOnAgainClickListener(this);
        mTab1.setOnSelectedChangeListener(this);

        mTab2.setOnAgainClickListener(this);
        mTab2.setOnSelectedChangeListener(this);

        mTab3.setOnAgainClickListener(this);
        mTab3.setOnSelectedChangeListener(this);
    }

    @Override
    public void onSelectedChange(View view, int index) {
        mTab1.setCurrentIndex(index);
        mTab2.setCurrentIndex(index);
        mTab3.setCurrentIndex(index);
        Toast.makeText(this, "index:" + index, Toast.LENGTH_SHORT).show();
        Fragment fragment = null;
        switch (index) {
            case 0:
                if (Fragment1 == null) {
                    Fragment1 = new Fragment1();
                }
                fragment = Fragment1;
                break;
            case 1:
                if (fragment2 == null) {
                    fragment2 = new Fragment2();
                }
                fragment = fragment2;
                break;
            case 2:
                if (fragment3 == null) {
                    fragment3 = new Fragment3();
                }
                fragment = fragment3;
                break;
            case 3:
                if (fragment4 == null) {
                    fragment4 = new Fragment4();
                }
                fragment = fragment4;
                break;
            case 4:
                if (fragment5 == null) {
                    fragment5 = new Fragment5();
                }
                fragment = fragment5;
                break;
        }
        switchPage(fragment, fragment.getClass().getSimpleName());
    }

    @Override
    public void onAgainClicked(View view, int index) {
        Toast.makeText(this, "index:" + index, Toast.LENGTH_SHORT).show();
    }


    /***显示对应的Fragment*/
    public void switchPage(Fragment fragment, String tag) {
        if (fragment == null) {
            return;
        }
        //第一次加载片段
        if (mCurFragment == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment, tag).commit();
            mCurFragment = fragment;
            return;
        }
        //相同的tab不做操作
        if (mCurFragment == fragment) {
            return;
        } else {
            if (fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mCurFragment).show(fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mCurFragment).add(R.id.fragment_layout, fragment, tag).commit();
            }
            mCurFragment = fragment;
        }
    }

}
