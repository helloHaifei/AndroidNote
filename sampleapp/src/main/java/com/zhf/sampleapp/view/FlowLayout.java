package com.zhf.sampleapp.view;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 *自动排列 child view，可以放在ScrollView下面
 * @author RAW
 */
public class FlowLayout extends ViewGroup {
    private int PAD_H = 20, PAD_V = 20; // Space between child views.
    private int mHeight;

    private int mMaxLine = Integer.MAX_VALUE;

    public void setMaxLine(int maxLine) {
        mMaxLine = maxLine;
    }

    public void setPaddingH(int paddingH){
        PAD_H = paddingH;
    }
    public void setPaddingV(int paddingV){
        PAD_V = paddingV;
    }

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);
        final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        final int count = getChildCount();
        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();
        int childHeightMeasureSpec;
        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
        } else {
            childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        mHeight = 0;
        int lineCount = 1;
        for(int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if(child.getVisibility() != GONE) {
                child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), childHeightMeasureSpec);
                final int childw = child.getMeasuredWidth();
                mHeight = Math.max(mHeight, child.getMeasuredHeight() + PAD_V);

                if(xpos + childw > width) {
                    lineCount++;
                    if (lineCount > mMaxLine){
                        break;
                    }
                    xpos = getPaddingLeft();
                    ypos += mHeight;
                }
                xpos += childw + PAD_H;

            }

        }
        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            height = ypos + mHeight;
        } else if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            if(ypos + mHeight < height) {
                height = ypos + mHeight;
            }
        }
        height += 5; // Fudge to avoid clipping bottom of last row.
        setMeasuredDimension(width, height);
    } // end onMeasure()

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = 1;
        final int width = r - l;
        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();
        for(int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if(child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                final int childh = child.getMeasuredHeight();
                if(xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += mHeight;
                    lineCount++;
                }
                if (lineCount > mMaxLine){
                    return;
                }
                child.layout(xpos, ypos, xpos + childw, ypos + childh);
                xpos += childw + PAD_H;
            }
        }
    } // end onLayout()
}
