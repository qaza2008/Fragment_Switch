package com.androidso.FragmentSwitchDemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import com.androidso.FragmentSwitchDemo.fragment.FragmentA;
import com.androidso.FragmentSwitchDemo.fragment.FragmentB;

public class MyActivity extends FragmentActivity implements View.OnClickListener {
    private FrameLayout container;
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private Fragment mCurrentFragment;
    private Button container_tab1, container_tab2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        if (savedInstanceState != null) {
            fragmentA = (FragmentA) getSupportFragmentManager().findFragmentByTag(FragmentA.TAG);
            fragmentB = (FragmentB) getSupportFragmentManager().findFragmentByTag(FragmentB.TAG);
        }

        initView();
    }

    private void initView() {
        container_tab1 = (Button) findViewById(R.id.container_tab1);
        container_tab2 = (Button) findViewById(R.id.container_tab2);
        container_tab1.setOnClickListener(this);
        container_tab2.setOnClickListener(this);
        container = (FrameLayout) findViewById(R.id.homepager_container);
        container_tab1.performClick();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.container_tab1:
                container_tab1.setTextColor(Color.parseColor("#ffffff"));
                container_tab2.setTextColor(Color.parseColor("#dddddd"));

                if (container_tab1.getTag() == null) {
                    container_tab1.setTag("1");
                    fragmentA = new FragmentA();
                    showContent(fragmentA);
                } else {
                    switchContent(fragmentB, fragmentA);
                }
                break;
            case R.id.container_tab2:
                container_tab2.setTextColor(Color.parseColor("#ffffff"));
                container_tab1.setTextColor(Color.parseColor("#dddddd"));
                if (container_tab2.getTag() == null) {
                    container_tab2.setTag("1");
                    fragmentB = new FragmentB();
                    showContent(fragmentB);
                } else {
                    switchContent(fragmentA, fragmentB);
                }
                break;
        }

    }

    public void switchContent(Fragment from, Fragment to) {

        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    .setCustomAnimations(
//                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (!to.isAdded()) {    // 先判断是否被add过
                String tag = "";
                if (to == fragmentA) {
                    tag = FragmentA.TAG;
                } else if (to == fragmentB) {
                    tag = FragmentB.TAG;
                }

                transaction.hide(from).add(container.getId(), to, tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    public void showContent(Fragment cententFragment) {

        if (mCurrentFragment != cententFragment) {
            mCurrentFragment = cententFragment;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!cententFragment.isAdded()) {    // 先判断是否被add过
                String tag = "";
                if (cententFragment == fragmentA) {
                    tag = FragmentA.TAG;
                } else if (cententFragment == fragmentB) {
                    tag = FragmentB.TAG;
                }

                transaction.add(container.getId(), cententFragment, tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.show(cententFragment).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
