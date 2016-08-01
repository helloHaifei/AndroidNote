package com.zhf.sampleapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhf.sampleapp.R;
import com.zhf.sampleapp.view.FixedGridLayout;


/**
 * 
 * @author: Haifei
 * @data：2015-3-25
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class FixedGridLayoutFragment extends BaseFragment {
	private FixedGridLayout mFixedGridLayout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout root = new LinearLayout(mContext);
		root.setClipChildren(false);
		mFixedGridLayout = new FixedGridLayout(mContext);
		
		mFixedGridLayout.setBackgroundColor(getResources().getColor(R.color.blue));
		mFixedGridLayout.setCellWidth(470);
		mFixedGridLayout.setCellHeight(200);
		
		//DraggableGridView dragGridView = new DraggableGridView(mContext);
		for (int i = 0; i < 10; i++) {
			//Button btn = new Button(mContext);
			//btn.setWidth(400);
			//btn.setHeight(200);
			//btn.setText(""+i);
			
			PictureItem btn = new PictureItem(mContext);
			//btn.setLayoutParams(new LayoutParams(460,200));
			
			mFixedGridLayout.addView(btn);
			//dragGridView.addView(btn);
		}

		root.addView(mFixedGridLayout,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		//root.addView(dragGridView,new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		return root;
	}
	
	class PictureItem extends RelativeLayout{

        public ImageView mImageView;
        public ImageView mDeleteView;
        Context mContext;
        public PictureItem(Context context) {
            super(context);
            mContext = context;
            init();
        }

        public PictureItem(Context context, AttributeSet attrs) {
            super(context, attrs);
            mContext = context;
            init();
        }

        void init(){
            // 正文图片
            //int imageWidth = ScreenUtils.dpToPxInt(mContext,85);
           // int imageHeight = ScreenUtils.dpToPxInt(mContext,85);
            mImageView = new ImageView(mContext);
            mImageView.setId(101);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setImageResource(R.drawable.ic_launcher);
            int imageMargin = 20;
            //mImageView.setPadding(imagePadding,imagePadding,imagePadding,imagePadding);
            LayoutParams imageParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            imageParam.setMargins(imageMargin,imageMargin,imageMargin,imageMargin);
            this.addView(mImageView, imageParam);

            // 删除按钮
            mDeleteView = new ImageView(mContext);
            mDeleteView.setImageResource(R.mipmap.ic_owner);
            LayoutParams deleteParam = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            deleteParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            this.addView(mDeleteView, deleteParam);

            //this.setBackgroundResource(R.drawable.common_bg_rounded_rectangle_gray_border);
        }
    }

}