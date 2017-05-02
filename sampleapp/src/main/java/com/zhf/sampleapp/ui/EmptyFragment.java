package com.zhf.sampleapp.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhf.sampleapp.view.FixedGridLayout;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class EmptyFragment extends BaseFragment {
	private FixedGridLayout mFixedGridLayout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout root = new LinearLayout(mContext);

        Button btn = new Button(getContext());
        btn.setWidth(400);
        btn.setHeight(200);
        btn.setText("连续登录3天 经验值+6\n再有24积分就升级了");
        Toast toast = new Toast(getContext());
        toast.setView(btn);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();

		return root;
	}



}