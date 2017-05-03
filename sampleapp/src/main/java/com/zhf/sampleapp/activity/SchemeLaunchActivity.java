package com.zhf.sampleapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zhf.common.BaseActivity;
import com.zhf.common.view.HfUtilWidget;

public class SchemeLaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(getContext());
        textView.setText("scheme 启动");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("autohomecamera://liveshow/liveshowdetail?roomid=2052"));
                try {
                    startActivity(intent);
                } catch (Exception e){
                    HfUtilWidget.showToast(getContext(), "not found activity");
                }
            }
        });

        setContentView(textView);

        textView.setText("scheme 启动 " + parserScheme(getIntent()));

    }

    public String parserScheme(Intent intent) {

        Uri uri = intent.getData();
        if (uri == null || uri.getHost() == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder()
                .append("\nscheme:")
                .append(uri.getScheme())
                .append("\nhost:")
                .append(uri.getHost())
                .append("\npath:")
                .append(uri.getPath())
                .append("\nparam roomid is:")
                .append(uri.getQueryParameter("roomid"));

        return sb.toString();

    }

}
