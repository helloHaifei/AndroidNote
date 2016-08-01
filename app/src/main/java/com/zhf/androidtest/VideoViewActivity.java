package com.zhf.androidtest;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoViewActivity extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_view);
        videoView = (VideoView) findViewById(R.id.videoView);
        /**
         * VideoView控制视频播放的功能相对较少，具体而言，它只有start和pause方法。为了提供更多的控制，
         * 可以实例化一个MediaController，并通过setMediaController方法把它设置为VideoView的控制器。
         */
        videoView.setMediaController(new MediaController(this));
        Uri videoUri = Uri.parse("/mnt/extSdCard/DCIM/Camera/20160627_155826.mp4");
//        Uri videoUri = Uri.parse("http://7xq3fp.com1.z0.glb.clouddn.com/Patterns-Why60fps.mp4");
        videoView.setVideoURI(videoUri);
        videoView.start();
    }
}
