package com.zhf.sampleapp.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhf.sampleapp.R;
import com.zhf.sampleapp.view.FixedGridLayout;
import com.zhf.sampleapp.view.FlowLayout;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class TagFragment extends BaseFragment {

	FlowLayout flowLayout;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		root = new LinearLayout(mContext);

		flowLayout = new FlowLayout(getContext());
		flowLayout.setMaxLine(2);
		flowLayout.setBackgroundColor(0xffabcdef);
		flowLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		init();
		return flowLayout;
	}

	void init(){
		for (int i=0; i < 10; i++){
			TopicDescribeTag topicDescribeTag = new TopicDescribeTag(getActivity().getLayoutInflater(),"test"+i);
			flowLayout.addView(topicDescribeTag.getView());
		}


//		flowLayout.addView(itemLayout);
	}

	public class TopicDescribeTag{
		Context mContext;
		LayoutInflater inflater;

		RelativeLayout itemLayout;

		public TopicDescribeTag( LayoutInflater inflater, String text){
//			mContext = context;
			itemLayout = (RelativeLayout)inflater.inflate(R.layout.topic_describe_tag_item_layout, null);
			TextView textView = (TextView) itemLayout.findViewById(R.id.tag_item_text);
			ColorDrawable colorDrawable = new ColorDrawable();
			int radii = 20;
			float[] outerRadii = {radii,radii,radii,radii,radii,radii,radii,radii};
			RoundRectShape roundRectShape = new RoundRectShape(outerRadii,null,null);
			ShapeDrawable shapeDrawable =new ShapeDrawable(roundRectShape);
			shapeDrawable.getPaint().setColor(0xffff0000);

			textView.setBackgroundDrawable(shapeDrawable);
			textView.setText(text);

		}


		public RelativeLayout getView(){
			return itemLayout;
		}
	}

	public class TopicDescribe extends RelativeLayout{

		public TopicDescribe(Context context, AttributeSet attrs) {
			super(context, attrs);
			getLayoutInflater(null).inflate(R.layout.topic_describe_tag_item_layout,this);
		}
	}

}