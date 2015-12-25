title: 2个Fragment之间的切换
---
date: 2015-12-22 15:31:11
tags: android fragment 切换
---



### 一.概述

  先展示下效果图,类似于这样的效果
*  Fragment在需要展示的时候创建
*  2个ragment切换互不影响 

下面一个是原版效果,一个是简版效果


![image](http://7vihs8.com1.z0.glb.clouddn.com/fragment1.gif)
![image](http://7vihs8.com1.z0.glb.clouddn.com/fragment2.gif)


#### 引用:
环境: idea  ant编译

源码地址: [https://github.com/qaza2008/Fragment_Switch](https://github.com/qaza2008/Fragment_Switch)


### 二.代码示例

1. MyActivity 实例代码

```
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
```
* initView()方法用于初始化ui,并且container_tab1.performClick();点击左侧按钮,使页面进入FragmentA.
* 主要方法在showContent(Fragment) 和 switchContent(Fragment,Fragment)
```
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

```


```
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
```

* 整体流程如下,当点击按钮时,通过按钮的tag是否为空判断Fragment是否创建,没创建就创建Fragment并且调用 `showContent()` 方法,如果创建就调用 `switchContent`方法.
* 整体切换`Fragment`全靠`FragmentTransaction`调用,包括 `add()`,`show()`,`hide()`等方法.

其实还是很好用的方法.

done
===

Welcome To My Website
---

[http://androidso.com](http://androidso.com)
---
