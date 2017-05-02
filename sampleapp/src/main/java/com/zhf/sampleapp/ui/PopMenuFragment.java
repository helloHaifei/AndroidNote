package com.zhf.sampleapp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhf.sampleapp.R;

import org.xml.sax.XMLReader;


/**
 * 
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class PopMenuFragment extends BaseFragment {


    private PostButton mPostBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout root = new RelativeLayout(mContext);

        root.setBackgroundColor(0xffefffff);
        root.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        root.setClickable(true);

        mPostBtn = new PostButton(getContext());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(0,0,100,100);

        mPostBtn.setLayoutParams(params);

        root.addView(mPostBtn);


        return root;
    }


}