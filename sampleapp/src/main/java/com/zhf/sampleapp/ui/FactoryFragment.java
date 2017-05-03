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
public class FactoryFragment extends BaseFragment {

    View rootView;

    public FactoryFragment(View view){
        rootView = view;
    }

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return rootView;
	}



}