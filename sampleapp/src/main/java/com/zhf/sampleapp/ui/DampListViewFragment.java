package com.zhf.sampleapp.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zhf.sampleapp.view.DampListView;

public class DampListViewFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		DampListView mDampListView = new DampListView(getActivity());
		mDampListView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mStrings));
		return mDampListView;
	}

	String[] mStrings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
			"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "0" };

}
