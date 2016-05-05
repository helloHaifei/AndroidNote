/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhf.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.zhf.common.R;
import com.zhf.common.interfaces.BaseInitialization;

/**
 * 存放一些独立的方法
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseInitialization{

    /***/
    private static final int NOT_FOR_RESULT = -1;
    protected BaseActivity mMe = BaseActivity.this;
    //  private ObjectGraph activityScopeGraph
    private LinearLayout mBaseRootLayout;
    private View mStatusBarView;

    boolean isShowSoftMethod ;
    @Override
    public <T extends View> T findView(@IdRes int resId){
        return (T) findViewById(resId);
    }

    @Override
    public <T extends View> T findView(View rootView, @IdRes int resId){
        return (T) rootView.findViewById(resId);
    }
    @Override
    public <T extends View> T inflate(@IdRes int resId){
        return (T) LayoutInflater.from(this).inflate(resId,null);
    }
    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public void launchActivity(Class activityClass) {
        launchActivity(activityClass, null, TransitionType.DEFAULT);
    }

    @Override
    public void launchActivity(Class activityClass, Bundle bundle) {
        launchActivity(activityClass, bundle, TransitionType.DEFAULT);
    }

    @Override
    public void launchActivity(Class activityClass, Bundle bundle, TransitionType transitionType) {
        launchActivity(activityClass, bundle, NOT_FOR_RESULT, transitionType);
    }

    @Override
    public void launchActivity(Class activityClass, Bundle bundle, int requestCode) {
        launchActivity(activityClass, bundle, requestCode, TransitionType.DEFAULT);
    }

    @Override
    public void launchActivity(Class activityClass, Bundle bundle, int requestCode, TransitionType transitionType) {
        Intent intent = new Intent(mMe, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (requestCode > 0) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        doTransitionAction(transitionType, true);
    }

    /**
     * 执行activity的overridePendingTransition。
     *
     * @param type  动画类型
     * @param start 判断是startActivity还是finsh
     */
    void doTransitionAction(TransitionType type, boolean start) {
        if (start) {
            switch (type) {
                case DEFAULT:
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                case PUSH_LEFT_RIGHT:
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    break;
                case PUSH_UP_DOWN:
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    break;
                case ZOOM_IN:
                    overridePendingTransition(R.anim.activity_pic_in, R.anim.activity_pic_normal);
                case SYSTEM:
                    break;
                default:
            }
        } else {
            switch (type) {
                case DEFAULT:
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
                case PUSH_LEFT_RIGHT:
                    overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    break;
                case PUSH_UP_DOWN:
                    overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                    break;
                case ZOOM_IN:
                    overridePendingTransition(R.anim.activity_pic_normal, R.anim.activity_pic_out);
                case SYSTEM:
                    break;
                default:
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        doTransitionAction(TransitionType.DEFAULT, false);
    }

    @Override
    public void finish(TransitionType transitionType) {
        super.finish();
        doTransitionAction(transitionType, false);
    }

    // 隐藏键盘
    public void hideKeyBoard() {
        try {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 显示键盘
    public void showKeyBoard(View view) {
        try {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSoftMethodListener(){
        //监听软键盘
        final View activityRootView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                activityRootView.getWindowVisibleDisplayFrame(r);

                int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
                if (heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
                    isShowSoftMethod = true;
                }else{
                    isShowSoftMethod = false;
                }

            }
        });
    }

    public boolean isShowSoftMethod() {
        return isShowSoftMethod;
    }
}
