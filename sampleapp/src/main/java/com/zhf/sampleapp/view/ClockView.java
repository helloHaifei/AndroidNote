package com.zhf.sampleapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ClockView extends View {

	public ClockView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int mWidth = getWidth();
		int mHeight = getHeight();
		int cX = mWidth/2;
		int cY = mHeight/2;
		Paint paintCircle = new Paint();
		paintCircle.setStyle(Paint.Style.STROKE);
		paintCircle.setStrokeWidth(5);
		canvas.drawCircle(cX, cY, mWidth/2, paintCircle);
		
		Paint paintDegree = new Paint();
		paintDegree.setTextSize(24);
		for (int i = 0; i < 12; i++) {
			if( i%3 == 0){
				paintDegree.setStrokeWidth(5);
				String degree = String.valueOf(i);
				canvas.drawLine(cX, mHeight/2-mWidth/2, cX, mHeight/2-mWidth/2 +60, paintDegree);
				canvas.drawText(degree, cX-paintDegree.measureText(degree)/2, mHeight/2-mWidth/2 +90, paintDegree);
				
			}else{
				paintDegree.setStrokeWidth(3);
				String degree = String.valueOf(i);
				canvas.drawLine(cX, mHeight/2-mWidth/2, cX, mHeight/2-mWidth/2 +30, paintDegree);
				canvas.drawText(degree, cX-paintDegree.measureText(degree)/2, mHeight/2-mWidth/2 +60, paintDegree);
				
			}
			canvas.rotate(30,cX,cY);
		}
		
		Paint paintHour = new Paint();
		paintHour.setStrokeWidth(20);
		Paint paintMinute = new Paint();
		paintMinute.setStrokeWidth(10);
		canvas.save();
		canvas.translate(cX, cY);
		canvas.drawLine(0, 0, 100, 100, paintHour);
		canvas.drawLine(0, 0, 100, 200, paintMinute);
		canvas.restore();
	}
}
