package com.zhf.sampleapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment{

	Context mContext;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

		view.setClickable(true);

		super.onViewCreated(view, savedInstanceState);
	}
}
