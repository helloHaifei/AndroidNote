package com.zhf.sampleapp.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zhf.sampleapp.R;
import com.zhf.sampleapp.view.FixedGridLayout;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class EmptyFragment extends BaseFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.activity_main,null);

		return rootView;
	}



}