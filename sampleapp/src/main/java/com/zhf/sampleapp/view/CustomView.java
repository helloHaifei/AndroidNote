package com.zhf.sampleapp.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhf.sampleapp.R;

public class CustomView extends LinearLayout {
	
	TextView titleTv;
	TextView contentTv;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(LinearLayout.VERTICAL);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
		titleTv = new TextView(getContext());
		titleTv.setText(typedArray.getString(R.styleable.CustomView_titleText));
		this.addView(titleTv);

		contentTv = new TextView(getContext());
		contentTv.setText(typedArray.getString(R.styleable.CustomView_contentText));
		this.addView(contentTv);

		typedArray.recycle();
	}

}
