package com.zhf.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * 用于在ListView,ScrollView中的Button
 * 在实例化时有记录位置position
 * 
 * @author fei
 *
 */
public class ItemButton extends Button {
	
	int mPosition;
	OnMyClickListener mListener;

	public ItemButton(Context context) {
		this(context, null);
	}
	public ItemButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mListener !=null){
					mListener.onMyClick(v, mPosition);
				}
				
			}
		});
	}
	
	public interface OnMyClickListener {
		public void onMyClick(View view, int position);
	}
	
	public void setOnMyClickListener(OnMyClickListener listener){
		mListener = listener;
	}
	
	public void setPosition(int position){
		this.mPosition = position;
	}
	

}
