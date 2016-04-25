package com.zhf.common;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zhf.common.interfaces.BaseInitialization;


/***
 * <p>A simple {@link Fragment} subclass.</p>
 * <p>继承BaseFragment的子类可以直接调finish()方法。</p>
 */
public abstract class BaseFragment extends Fragment implements BaseInitialization {
    protected BaseActivity mBaseActivity;
    /***/
    private static final int NOT_FOR_RESULT = -1;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mBaseActivity = (BaseActivity) activity;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public <T extends View> T findView(int resId) {
        return (T) getActivity().findViewById(resId);
    }

    @Override
    public void launchActivity(Class activityClass, Bundle bundle) {
        launchActivity(activityClass, bundle, NOT_FOR_RESULT, TransitionType.DEFAULT);
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
//        mBaseActivity.launchActivity(activityClass, bundle, requestCode, transitionType);
        Intent intent = new Intent(mBaseActivity, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (requestCode > 0) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivity(intent);
        }
        mBaseActivity.doTransitionAction(transitionType, true);
    }

    @Override
    public void finish() {
        mBaseActivity.finish();
    }

    @Override
    public void finish(TransitionType transitionType) {
        mBaseActivity.finish(transitionType);
    }



}
