package com.zhf.common.interfaces;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 */
public interface BaseInitialization {

    <T extends View> T findView(@IdRes int resId);
    <T extends View> T findView(View rootView, @IdRes int resId);
    <T extends View> T inflate(@LayoutRes int layoutId);


    void launchActivity(Class activityClass, Bundle bundle);

    void launchActivity(Class activityClass, Bundle bundle, TransitionType transitionType);

    void launchActivity(Class activityClass, Bundle bundle, int requestCode);

    void launchActivity(Class activityClass, Bundle bundle, int requestCode, TransitionType transitionType);

    void finish();

    void finish(TransitionType transitionType);

    /***
     * 过度动画
     */
    enum TransitionType {
        SYSTEM, DEFAULT, FADE, PUSH_LEFT_RIGHT, PUSH_UP_DOWN, ZOOM_IN
    }
}
