package com.zhf.sampleapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.zhf.sampleapp.R;


public class RainbowProcessBar extends View{

	//progress bar color
	int barColor = Color.parseColor("#1e8899");
	//every bar segment width
	int hSpace = dip2px(80);
	//every bar segment height
	int vSpace = dip2px(4);
	//space among bars
	int space = dip2px(10);
	float startX = 0;
	float delta = 10f;
	Paint mPaint;
	
	public RainbowProcessBar(Context context) {
		super(context);
	}
	public RainbowProcessBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public RainbowProcessBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context,attrs, defStyleAttr);
		//read custom attrs
		TypedArray t = context.obtainStyledAttributes(attrs,R.styleable.rainbowbar,0,0);
		hSpace = t.getDimensionPixelSize(R.styleable.rainbowbar_rainbowbar_hspace, hSpace);
		vSpace = t.getDimensionPixelOffset(R.styleable.rainbowbar_rainbowbar_vspace, vSpace);
		barColor = t.getColor(R.styleable.rainbowbar_rainbowbar_color, barColor);
		t.recycle();
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(barColor);
		mPaint.setStrokeWidth(vSpace);
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	int index = 0;
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int sw = getMeasuredWidth();
		if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
	        startX = 0;
	    } else {
	        startX += delta;
	    }
		
		float start = startX;
	    // draw latter parse
	    while (start < sw) {
	        canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
	        start += (hSpace + space);
	    }
	    start = startX - space - hSpace;
	 // draw front parse
	    while (start >= -hSpace) {
	        canvas.drawLine(start, 5, start + hSpace, 5, mPaint);
	        start -= (hSpace + space);
	    }
	    
	    if (index >= 700000) {
	        index = 0;
	    }
	    invalidate();
	}
	

	public int dip2px(float dipValue) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}



}
