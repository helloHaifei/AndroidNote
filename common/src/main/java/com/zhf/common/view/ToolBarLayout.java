package com.zhf.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhf.common.R;

/**
 * |----------------------------------------------------------|
 * |leftLayout              middleLayout           rightLayout|
 * |      ^                     ^                      ^      |
 * |     View                 textView              View      |
 * |
 * |----------------------------------------------------------|
 * <p/>
 * Created by zhf on 16/8/4.
 */
public class ToolBarLayout extends RelativeLayout {

    LinearLayout mLeftLayout, mRightLayout, mMiddleLayout;

    TextView mTitleView;
//    View mLeftView;
//    View mRightView;

    int textColor;
    int textSize ;//dp
    int mViewMinWidth;
    int mViewPadding;

    public ToolBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTitleBar(context, attrs);
    }

    public ToolBarLayout(Context context) {
        super(context);
        initTitleBar(context, null);

    }

    public void initTitleBar(Context context, AttributeSet attrs) {

        textColor = getResources().getColor(R.color.titleBarTextViewColor);
        textSize = getResources().getDimensionPixelSize(R.dimen.titleBarTextViewSize);
        mViewPadding = getResources().getDimensionPixelSize(R.dimen.titleBarTextViewPadding);

        mViewMinWidth = getResources().getDimensionPixelSize(R.dimen.titleBarMinWidth);

        mLeftLayout = new LinearLayout(getContext());
        mMiddleLayout = new LinearLayout(getContext());
        mRightLayout = new LinearLayout(getContext());


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);

//        mMinBtnWidth = context.getResources().getDimensionPixelSize(R.dimen.title_bar_btn_minwidth);

        /* title layout*/
        CharSequence titleText = a.getText(R.styleable.ToolBar_centerText);
        Integer titleTextColor = a.getColor(R.styleable.ToolBar_centerTextColor, -1);
        Integer titleTextSize = a.getDimensionPixelSize(R.styleable.ToolBar_centerTextSize, -1);

        mTitleView = new TextView(getContext());
        mTitleView.setText(titleText);
        if (titleTextColor != -1) {
            mTitleView.setTextColor(titleTextColor);
        }
        if (titleTextSize != -1) {
            mTitleView.setTextSize(titleTextSize);
        }

        mMiddleLayout.addView(mTitleView);

        /*left layout*/
        CharSequence leftText = a.getText(R.styleable.ToolBar_leftText);
        Integer leftTextColor = a.getColor(R.styleable.ToolBar_leftTextColor, -1);
        Integer leftTextSize = a.getDimensionPixelSize(R.styleable.ToolBar_leftTextSize, -1);
        Drawable leftDrawable = a.getDrawable(R.styleable.ToolBar_leftDrawable);

        if (leftDrawable != null) {
            ImageButton leftImageButton = createImageButton();
            leftImageButton.setImageDrawable(leftDrawable);
            leftImageButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).finish();
                    }
                }
            });
            mLeftLayout.addView(leftImageButton);
        } else if (!TextUtils.isEmpty(leftText)) {
            TextView leftTextView = createTextView(leftText);

            if (leftTextColor != -1) {
                leftTextView.setTextColor(leftTextColor);
            }
            if (leftTextSize != -1) {
                leftTextView.setTextSize(leftTextSize);
            }
            mLeftLayout.addView(leftTextView);
        } else {

        }

        /*right layout*/
        CharSequence rightText = a.getText(R.styleable.ToolBar_rightText);
        Integer rightTextColor = a.getColor(R.styleable.ToolBar_rightTextColor, -1);
        Integer rightTextSize = a.getDimensionPixelSize(R.styleable.ToolBar_rightTextSize, -1);
        Drawable rightDrawable = a.getDrawable(R.styleable.ToolBar_rightDrawable);

        if (rightDrawable != null) {
            ImageButton leftImageButton = createImageButton();
            leftImageButton.setImageDrawable(leftDrawable);
            mRightLayout.addView(leftImageButton);
        } else if (!TextUtils.isEmpty(rightText)) {

            TextView rightView = createTextView(rightText);

            if (rightTextColor != -1) {
                rightView.setTextColor(rightTextColor);
            }
            if (rightTextSize != -1) {
                rightView.setTextSize(rightTextSize);
            }
            mRightLayout.addView(rightView);
        }

        a.recycle();

        RelativeLayout.LayoutParams leftLayoutparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftLayoutparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftLayoutparams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mLeftLayout, leftLayoutparams);

        RelativeLayout.LayoutParams rightLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mRightLayout, rightLayoutParams);


        RelativeLayout.LayoutParams middleLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        middleLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mMiddleLayout, middleLayoutParams);
    }

    public LinearLayout getLeftLayout() {
        return mLeftLayout;
    }

    public LinearLayout getMiddleLayout() {
        return mMiddleLayout;
    }

    public LinearLayout getRightLayout() {
        return mRightLayout;
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public void setTitle(CharSequence text) {
        mTitleView.setText(text);
    }

    public void setTitle(int resId) {
        mTitleView.setText(resId);
    }

    public View getLeftView() {
        if (mLeftLayout.getChildCount() > 0) {
            return mLeftLayout.getChildAt(0);
        } else {
            return null;
        }
    }

    public void addLeftView(View view) {
        mLeftLayout.addView(view);
    }

    public View getRightView() {
        if (mRightLayout.getChildCount() > 0) {
            return mRightLayout.getChildAt(0);
        } else {
            return null;
        }
    }

    public void addRightView(View view) {
        mRightLayout.addView(view);
    }

    /**
     * 创建定义好样式的TextView
     *
     * @return
     */
    public TextView createTextView() {

//        AttributeSet
        TextView textView = new TextView(getContext());
        textView.setMinWidth(mViewMinWidth);
        textView.setPadding(mViewPadding,0, mViewPadding,0);
        textView.setGravity(Gravity.CENTER);
        //textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        textView.setTextSize(textSize);
        textView.setTextColor(textColor);
        return textView;
    }

    /**
     * see link:createTextView()
     *
     * @param text
     * @return
     */
    public TextView createTextView(CharSequence text) {
        TextView textView = createTextView();
        textView.setText(text);
        return textView;
    }

    public TextView createTextView(int resId) {
        return createTextView(getResources().getString(resId));
    }

    public ImageButton createImageButton() {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setMinimumWidth(mViewMinWidth);
        imageButton.setPadding(mViewPadding, 0, mViewPadding, 0);
        imageButton.setBackgroundColor(0x00000000);
        return imageButton;
    }

    public ImageButton createImageButton(int resId) {
        ImageButton imageButton = createImageButton();
        imageButton.setImageResource(resId);
        return imageButton;
    }

}
