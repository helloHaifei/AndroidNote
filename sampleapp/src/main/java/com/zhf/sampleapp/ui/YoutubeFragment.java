package com.zhf.sampleapp.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhf.sampleapp.R;
import com.zhf.sampleapp.view.FixedGridLayout;
import com.zhf.sampleapp.view.YoutubeLayout;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class YoutubeFragment extends BaseFragment {
	private FixedGridLayout mFixedGridLayout;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_youtube,null);

        final TextView viewHeader = (TextView) rootView.findViewById(R.id.header);
        final YoutubeLayout youtubeLayout = (YoutubeLayout) rootView.findViewById(R.id.dragLayout);
        final ListView listView = (ListView) rootView.findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewHeader.setText(listView.getAdapter().getItem(i).toString());
                youtubeLayout.setVisibility(View.VISIBLE);
                youtubeLayout.maximize();
            }
        });

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public String getItem(int i) {
                return "object" + i;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View rView, ViewGroup viewGroup) {
                View view = rView;
                if (view == null) {
                    view = getActivity().getLayoutInflater().inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                }
                ((TextView) view.findViewById(android.R.id.text1)).setText(getItem(i));
                return view;
            }
        });

		return rootView;
	}



}