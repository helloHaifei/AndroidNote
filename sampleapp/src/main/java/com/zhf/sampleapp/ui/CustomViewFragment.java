package com.zhf.sampleapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhf.sampleapp.R;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class CustomViewFragment extends BaseFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.custom_view_layout, null);

		return rootView;
	}



}