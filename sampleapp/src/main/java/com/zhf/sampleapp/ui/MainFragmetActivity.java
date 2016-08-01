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

public class MainFragmetActivity extends FragmentActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comm_fragment_layout);
		initView();
	}

	List<Custom> mList;
	{
		mList = new ArrayList<Custom>();
		mList.add(new Custom("TextViw样式", TextViewSpanFragment.class));
		mList.add(new Custom("尼阻ListView", DampListViewFragment.class));
//		mList.add(new Custom("通知栏显示", NotificationActivity.class));
		mList.add(new Custom("SlidingPaneLayout", SlidingPaneLayoutFrabment.class));
		mList.add(new Custom("ProcessBar帧动画", ProcessBarFragment.class));
		mList.add(new Custom("自定义组件使用attr属性", CustomViewActivity.class));
		mList.add(new Custom("Drag YoutubeActivity", YoutubeActivity.class));
		mList.add(new Custom("FixedGridLayout", FixedGridLayoutFragment.class));
		
		//mList.add(new Custom("JAVA与HTML相互调用", JavascriptInterface.class));
	}

	FrameLayout mFrameLayout;
	
	void initView() {
		LinearLayout root = (LinearLayout) findViewById(R.id.comment_linearlayout);
		mFrameLayout = (FrameLayout) findViewById(R.id.comm_fragment_layout);
		for (Custom item : mList) {
			TextView tv = new TextView(this);
			tv.setTextSize(20);
			tv.setMinHeight(HfViewUtil.dip2px(this, 45));
			tv.setText(item.title);
			tv.setTag(item.activityClass);
			tv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Class<?> fragmentClass = (Class<?>) v.getTag();
					
					if(fragmentClass.getSuperclass().getName().toString().contains("Activity")){
						Intent intent=  new Intent(MainFragmetActivity.this, fragmentClass);
						startActivity(intent);
					}else{
						try {
							Fragment fragment = (Fragment) fragmentClass.newInstance();
							showFragmet(fragment);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			});

			root.addView(tv, new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));
		}
	}

	private void showFragmet(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.comm_fragment_layout, fragment);
		//ft.addToBackStack(null);
		ft.addToBackStack(fragment.getClass().getSimpleName());
		ft.commit();

		mFrameLayout.setBackgroundColor(getResources().getColor(R.color.white));
	}

	class Custom {
		String title;
		Class<?> activityClass;

		public Custom(String title, Class<?> c) {
			this.title = title;
			this.activityClass = c;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		mFrameLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
	}

}