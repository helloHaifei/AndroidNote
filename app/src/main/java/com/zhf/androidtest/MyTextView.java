package com.zhf.androidtest;

import android.content.Context;
import android.graphics.Paint;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zhf on 16/7/11.
 */
public class MyTextView extends TextView{

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


//    public void isOverFlowed(String value, int widthLL) {
//        Paint paint = getPaint();
//        float width = paint.measureText(value);
//        widthLL = widthLL - getPaddingLeft();
//        if (width > widthLL) {
//            for (int i = value.length() - 4; i > 0; i--) {
//                width = paint.measureText(ATStringUtil.concatString(value.substring(0, i), "..."));
//                if (width <= widthLL) {
//                    setText(ATStringUtil.concatString(value.substring(0, i), "..."));
//                    break;
//                }
//            }
//        }
//    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resize();
    }

    /**
     * 去除当前页无法显示的字
     * @return 去掉的字数
     */
    public int resize() {
        CharSequence oldContent = getText();
        CharSequence newContent = oldContent.subSequence(0, getCharNum());
        setText(newContent);
        return oldContent.length() - newContent.length();
    }

    /**
     * 获取当前页总字数
     */
    public int getCharNum() {
        return getLayout().getLineEnd(getLineNum());
    }

    /**
     * 获取当前页总行数
     */
    public int getLineNum() {
        Layout layout = getLayout();
        int topOfLastLine = getHeight() - getPaddingTop() - getPaddingBottom() - getLineHeight();
        return layout.getLineForVertical(topOfLastLine);
    }

}
