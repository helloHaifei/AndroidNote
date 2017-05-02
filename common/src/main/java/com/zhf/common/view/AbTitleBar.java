///*
// * Copyright (C) 2013 www.418log.org
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.zhf.common.view;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.text.TextPaint;
//import android.util.AttributeSet;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.common.utils.GMViewUtil;
//import com.goumin.forum.R;
//
///**
// * 描述：标题栏实现.
// *
// * @author zhaoqp
// * @date：2013-4-24 下午3:46:47
// * @version v1.0
// */
//public class AbTitleBar extends RelativeLayout {
//
//	/** The m context. */
//	private Activity mActivity;
//
//	/** 标题布局. */
//	protected LinearLayout titleTextLayout = null;
//
//	/** 显示标题文字的View. */
//	protected Button titleTextBtn = null;
//
//	/** 左侧的button. */
//	protected TextView leftBtn = null;
//
//	/** 左边的View，可以自定义显示什么. */
//	protected LinearLayout leftLayout = null;
//
//	/** 右边的View，可以自定义显示什么. */
//	protected LinearLayout rightLayout = null;
//
//	/** 标题栏布局ID. */
//	public int mAbTitleBarID = 1;
//
//	/** 全局的LayoutInflater对象，已经完成初始化. */
//	public LayoutInflater mInflater;
//
//
//
//	/** 下拉选择. */
//	private PopupWindow popupWindow;
//
//	public AbTitleBar(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		ininTitleBar(context,attrs);
//	}
//
//	public AbTitleBar(Context context) {
//		super(context);
//		ininTitleBar(context,null);
//
//	}
//
//	public void ininTitleBar(Context context,AttributeSet attrs){
//
//		mActivity  = (Activity)context;
//		//this.setGravity(Gravity.CENTER_VERTICAL);
//		RelativeLayout root = new RelativeLayout(context);
//		this.setTitleBarBackground(R.drawable.title_bg);
//		this.addView(root,LayoutParams.MATCH_PARENT, (int)(getResources().getDimension(R.dimen.title_bar_height)+0.5));
//
//		if(attrs == null){
//			this.setId(mAbTitleBarID);// mark haifei 2014-1-4
//		}
//		mInflater = LayoutInflater.from(context);
//
//		titleTextLayout = new LinearLayout(context);
//		titleTextLayout.setOrientation(LinearLayout.VERTICAL);
//		titleTextLayout.setGravity(Gravity.CENTER);
//		titleTextLayout.setPadding(0, 0, 0, 0);
//
//		titleTextBtn = new Button(context);
//		titleTextBtn.setTextColor(getContext().getResources().getColor(R.color.title_bar_text));
//		titleTextBtn.setTextSize(20);
//		titleTextBtn.setPadding(5, 0, 5, 0);
//		titleTextBtn.setBackgroundDrawable(null);
//		titleTextBtn.setSingleLine();
//		titleTextLayout.addView(titleTextBtn,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,1));
//
//		LayoutParams params  = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//		params.addRule(RelativeLayout.CENTER_IN_PARENT);
//		root.addView(titleTextLayout, params);
//
//		// 左边的布局
//		leftLayout = new LinearLayout(context);
//		leftLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//		LayoutParams leftLayoutParams  = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//		leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//		leftLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
//		root.addView(leftLayout,leftLayoutParams);
//
//		leftBtn = new TextView(context);
//		leftBtn.setVisibility(View.GONE);
//		leftBtn.setTextColor(getContext().getResources().getColor(R.color.title_bar_text));
//		leftBtn.setGravity(Gravity.CENTER_VERTICAL);
//		leftBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				mActivity.finish();
//			}
//		});
//		//RelativeLayout.LayoutParams params2  = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//		//params2.addRule(RelativeLayout.CENTER_VERTICAL);
//		leftLayout.addView(leftBtn);
//		//root.addView(titleTextLayout,titleTextLayoutParams);
//
//		// 加右边的布局
//		rightLayout = new LinearLayout(context);
//		rightLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//		LayoutParams rightParams  = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//		rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//		rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
//		root.addView(rightLayout,rightParams);
//		rightLayout.setVisibility(View.INVISIBLE);
//
//	}
//
//
//	/**
//	 * 描述：标题栏的背景图.
//	 * @param res  背景图资源ID
//	 */
//	public void setTitleBarBackground(int res) {
//		this.setBackgroundResource(res);
//	}
//
//	/**
//	 * 描述：设置标题背景.
//	 * @param d  背景图
//	 */
//	public void setTitleBarBackgroundDrawable(Drawable d) {
//		this.setBackgroundDrawable(d);
//	}
//
//	/**
//	 * 描述：标题栏的背景图.
//	 * @param color  背景颜色值
//	 */
//	public void setTitleBarBackgroundColor(int color) {
//		this.setBackgroundColor(color);
//	}
//
//
//
//
//	/**
//	 * 描述：标题文字字号.
//	 * @param titleTextSize  文字字号
//	 */
//	public void setTitleTextSize(int titleTextSize) {
//		this.titleTextBtn.setTextSize(titleTextSize);
//	}
//
//
//
//	/**
//	 * 描述：获取标题文本的Button.
//	 * @return the title Button view
//	 */
//	public Button getTitleTextButton() {
//		return titleTextBtn;
//	}
//
//	/**
//	 * 描述：获取标题Logo的View.
//	 * @return the logo view
//	 */
//	public TextView getLogoView() {
//		return leftBtn;
//	}
//
//	/**
//	 * 描述：设置标题字体粗体.
//	 *
//	 * @param bold the new title text bold
//	 */
//	public void setTitleTextBold(boolean bold){
//		TextPaint paint = titleTextBtn.getPaint();
//		if(bold){
//			//粗体
//			paint.setFakeBoldText(true);
//		}else{
//			paint.setFakeBoldText(false);
//		}
//
//	}
//
//	/**
//	 * 描述：设置标题背景.
//	 *
//	 * @param resId the new title text background resource
//	 */
//	public void setTitleTextBackgroundResource(int resId){
//		titleTextBtn.setBackgroundResource(resId);
//	}
//
//
//	/**
//	 * 描述：设置标题背景.
//	 *
//	 * @param drawable the new title text background drawable
//	 */
//	public void setTitleTextBackgroundDrawable(Drawable drawable){
//		titleTextBtn.setBackgroundDrawable(drawable);
//	}
//
//	/**
//     * 描述：设置标题文本.
//     * @param text  文本
//     */
//	public Button setTitleText(String text) {
//		titleTextBtn.setText(text);
//		return titleTextBtn;
//	}
//
//	/**
//     * 描述：设置标题文本.
//     * @param resId  文本的资源ID
//     */
//	public Button setTitleText(int resId) {
//		titleTextBtn.setText(resId);
//		return titleTextBtn;
//	}
//
//
//
//	/**
//     * 描述：设置左按钮的图标.
//     * @param resId  Logo资源ID
//     */
//	public TextView setLeftIcon(int resId) {
//		leftBtn.setVisibility(View.VISIBLE);
//		leftBtn.setBackgroundResource(resId);
//		return leftBtn;
//	}
//
//
//	/**
//     * 描述：设置左button
//     * @param resId  Logo资源ID
//     */
//	public TextView setLeftIcon(String text,int resId) {
//		leftBtn.setText(text);
//		leftBtn.setVisibility(View.VISIBLE);
//		leftBtn.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
//		leftBtn.setBackgroundColor(Color.TRANSPARENT);
//
//		return leftBtn;
//	}
//
//	/**
//     * 描述：把指定的View填加到标题栏右边.
//     * @param leftView  指定的View
//     */
//	public void addLeftView(View leftView) {
//		leftView.setMinimumWidth(GMViewUtil.dip2px(getContext(), 50));
//		//LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(HfViewUtil.dip2px(context, dipValue), height)
//		leftLayout.setVisibility(View.VISIBLE);
//		leftLayout.addView(leftView);
//	}
//
//	public Button setRightButton(String text){
//		Button rightBtn = new Button(getContext());
//		rightBtn.setText(text);
//		//rightBtn.setMinimumWidth(100);
//		rightBtn.setTextSize(15);
//		rightBtn.setPadding(10, 0, 10, 0);
//		rightBtn.setTextColor(getResources().getColor(R.color.orange));
//		rightBtn.setBackgroundColor(0x00000000);
//
//		addRightView(rightBtn);
//		return rightBtn;
//	}
//
//	public ImageButton setRightIcon(int drawableRes){
//		ImageButton rightBtn = new ImageButton(getContext());
//		//rightBtn.setMinimumWidth(100);
//		rightBtn.setPadding(10, 0, 10, 0);
//		rightBtn.setBackgroundColor(0x00000000);
//		rightBtn.setImageResource(drawableRes);
//		addRightView(rightBtn);
//		return rightBtn;
//	}
//
//	/**
//     * 描述：把指定的View填加到标题栏右边.
//     * @param rightView  指定的View
//     */
//	public void addRightView(View rightView) {
//		rightView.setMinimumWidth(GMViewUtil.dip2px(getContext(), 50));
//		//LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(HfViewUtil.dip2px(context, dipValue), height)
//		rightLayout.setVisibility(View.VISIBLE);
//		rightLayout.addView(rightView);
//	}
//
//	/**
//     * 描述：把指定资源ID表示的View填加到标题栏右边.
//     * @param resId  指定的View的资源ID
//     */
//	public void addRightView(int resId) {
//		rightLayout.setVisibility(View.VISIBLE);
//		rightLayout.addView(mInflater.inflate(resId, null));
//	}
//
//	/**
//     * 描述：清除标题栏右边的View.
//     */
//	public void clearRightView() {
//		rightLayout.removeAllViews();
//	}
//
//	/**
//	 * 获取这个右边的布局,可用来设置位置
//	 * @return
//	 */
//	public LinearLayout getRightLayout() {
//		return rightLayout;
//	}
//
//	/**
//	 * 描述：设置Logo按钮的点击事件.
//	 * @param mOnClickListener  指定的返回事件
//	 */
//	public void setLogoOnClickListener(OnClickListener mOnClickListener) {
//		 leftBtn.setOnClickListener(mOnClickListener);
//	}
//
//	/**
//	 * 描述：设置标题的点击事件.
//	 * @param mOnClickListener  指定的返回事件
//	 */
//	public void setTitleTextOnClickListener(OnClickListener mOnClickListener) {
//		titleTextBtn.setOnClickListener(mOnClickListener);
//	}
//
//	/**
//	 * 描述：下拉菜单的的实现方法
//	 * @param parent
//	 * @param view 要显示的View
//	 * @param offsetMode 不填满的模式
//	 */
//	public void showWindow(View parent,View view,boolean offsetMode) {
//		GMViewUtil.measureView(view);
//		int popWidth = parent.getMeasuredWidth();
//		int popMargin = (this.getMeasuredHeight()-parent.getMeasuredHeight())/2;
//		if(view.getMeasuredWidth()>parent.getMeasuredWidth()){
//			popWidth = view.getMeasuredWidth();
//		}
//		if(offsetMode){
//			popupWindow = new PopupWindow(view, popWidth+10, LayoutParams.WRAP_CONTENT, true);
//		}else{
//			popupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
//		}
//
//		// 使其聚集
//		popupWindow.setFocusable(true);
//		// 设置允许在外点击消失
//		popupWindow.setOutsideTouchable(true);
//		// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
//		popupWindow.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
//		popupWindow.showAsDropDown(parent,0, popMargin+2);
//	}
//
//	/**
//	 * 描述：隐藏Window
//	 */
//	public void hideWindow() {
//		if (popupWindow != null) {
//			popupWindow.dismiss();
//		}
//
//	}
//
//	/**
//	 *
//	 * 描述：设置标题下拉的View
//	 * @param view
//	 * @throws
//	 */
//	public void setTitleTextDropDown(final View view){
//		 if(view == null){
//			   return;
//		 }
//		 setTitleTextOnClickListener(new OnClickListener() {
//
//			 @Override
//			 public void onClick(View v) {
//				 showWindow(titleTextBtn,view,true);
//			 }
//		 });
//	}
//
//	/**
//	 * 获取标题的全体布局
//	 * @return
//	 */
//	public LinearLayout getTitleTextLayout() {
//		return titleTextLayout;
//	}
//
//
//
//}
