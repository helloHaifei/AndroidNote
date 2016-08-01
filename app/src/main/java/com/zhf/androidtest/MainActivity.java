package com.zhf.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zhf.common.BaseActivity;


public class MainActivity extends BaseActivity {

    MyTextView mTextView;
    String mString = "ABCDEFGHIJKLJNOPQRSTUVWXYZABCDEFGHIJKLJNOPQRSTUVWXYZABCDEFGHIJKLJNOPQRSTUVWXYZABCDEFGHIJKLJNOPQRSTUVWXYZABCDEFGHIJKLJNOPQRSTUVWXYZABCDEFGHIJKLJNOPQRSTUVWXYZABCDEFGHIJKLJNOPQRSTUVWXYZ";
    String mString2 = "一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";
    final String END = "...全文";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //launchActivity(VideoViewActivity.class);
        mTextView = findView(R.id.textview);

        mTextView.setText(mString2);
        mTextView.requestLayout();
        mTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("test",mTextView.getText().toString());
                Log.i("test","string length="+mTextView.getText().length());
                mTextView.setText(mTextView.getText().subSequence(0, mTextView.getText().length()- END.length()+1) + END);
                //mTextView.getText();
            }
        },200);

        Log.i("test","string length="+mString.length()+"");
        Log.i("test","string length="+mTextView.getText().length());

        EllipsizingTextView view = findView(R.id.textview2);
        view.setMaxLines(2);
    }
}
