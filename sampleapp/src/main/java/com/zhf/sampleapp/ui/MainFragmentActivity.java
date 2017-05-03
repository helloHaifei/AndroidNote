package com.zhf.sampleapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhf.common.util.HfViewUtil;
import com.zhf.sampleapp.R;
import com.zhf.sampleapp.view.ClockView;

public class MainFragmentActivity extends FragmentActivity {

    List<CustomActivity> mList;
    FrameLayout mFrameLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_fragment_layout);
        initData();
        initView();
    }

    void initData() {
        mList = new ArrayList<CustomActivity>();
        mList.add(new CustomActivity("TextViw样式", TextViewSpanFragment.class));
        mList.add(new CustomActivity("尼阻ListView", DampListViewFragment.class));
//		mList.add(new CustomActivity("通知栏显示", NotificationActivity.class));
        mList.add(new CustomActivity("SlidingPaneLayout", SlidingPaneLayoutFrabment.class));
        mList.add(new CustomActivity("ProcessBar帧动画", ProcessBarFragment.class));
        mList.add(new CustomActivity("自定义组件使用attr属性", CustomViewFragment.class));
        mList.add(new CustomActivity("Drag YoutubeFragment", YoutubeFragment.class));
        mList.add(new CustomActivity("FixedGridLayout", FixedGridLayoutFragment.class));
        mList.add(new CustomActivity("PopMenuFragment", PopMenuFragment.class));
        mList.add(new CustomActivity("TagFragment", TagFragment.class));
        mList.add(new CustomActivity("HtmlFragment", HtmlFragment.class));
        mList.add(new CustomActivity("javascriptInterface", JavascriptInterfaceFragment.class));
        mList.add(new CustomActivity("ClockView", new ClockView(this,null)));
    }

    void initView() {
        LinearLayout root = (LinearLayout) findViewById(R.id.comment_linearlayout);
        mFrameLayout = (FrameLayout) findViewById(R.id.comm_fragment_layout);
        for (CustomActivity item : mList) {
            TextView tv = new TextView(this);
            tv.setTextSize(20);
            tv.setMinHeight(HfViewUtil.dip2px(this, 45));
            tv.setText(item.title);
            tv.setTag(item);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    CustomActivity item = (CustomActivity) v.getTag();

                    Class<?> fragmentClass = item.getObjectClass();

                    if (fragmentClass.getSuperclass().getName().toString().contains("Activity")) {
                        Intent intent = new Intent(MainFragmentActivity.this, fragmentClass);
                        startActivity(intent);
                    } else if (fragmentClass.getSuperclass().getName().toString().contains("Fragment")) {
                        /* 方式一
                        try {
                            Fragment fragment = (Fragment) fragmentClass.newInstance();
                            showFragmet(fragment);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        */
                        Fragment fragment = Fragment.instantiate(MainFragmentActivity.this, fragmentClass.getName(), null);
                        showFragmet(fragment);
                    } else if (item.getView() != null) {
                        FactoryFragment fragment = new FactoryFragment(item.getView());
                        showFragmet(fragment);

                    }
                }
            });

            root.addView(tv, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private void showFragmet(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.add(R.id.comm_fragment_layout, fragment);
        //ft.addToBackStack(null);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();

        mFrameLayout.setBackgroundColor(getResources().getColor(R.color.white));
    }

    class CustomActivity {
        String title;
        Class<?> activityClass;
        View view;

        public CustomActivity(String title, Class<?> c) {
            this.title = title;
            this.activityClass = c;

        }

        public CustomActivity(String title, View view) {
            this.title = title;
            this.view = view;
            activityClass = view.getClass();
        }

        public Class<?> getObjectClass() {
            return activityClass;
        }

        public String getTitle() {
            return title;
        }

        public View getView() {
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mFrameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }

}