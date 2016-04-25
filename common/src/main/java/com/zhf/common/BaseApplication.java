package com.zhf.common;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

public abstract class BaseApplication extends Application {
	protected ArrayList<Activity> iActivityStack = new ArrayList<Activity>();
	protected static BaseApplication iApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		iApplication = this;
	}

	/** 将当前activity加入栈 */
	public void addActivityToStack(Activity activity) {
		iActivityStack.add(activity);
	}

	/** 将activity移出栈 */
	public void removeActivityFromStack(Activity activity) {
		iActivityStack.remove(activity);
	}

	/** 清除activity栈 */
	public void clearActivityStack() {
		iActivityStack.clear();
	}

	/**
	 * 结束所有的activity
	 */
	public void finishAllActivity(){
		if (iActivityStack!=null && iActivityStack.size() > 0) {
			for(int i = iActivityStack.size() - 1; i >= 0;i--) {
				Activity activity = iActivityStack.get(i);
				iActivityStack.remove(activity);
				if (!activity.isFinishing()) {
					activity.finish();
				}
			}
		}
	}

	/** 清除最上层的activity */
	public void removeActivityStackTop() {
		int size = iActivityStack.size();
		if (size > 1) {
			Activity activity = iActivityStack.get(size - 1);
			iActivityStack.remove(activity);
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	public static BaseApplication getMyApplication() {
		return iApplication;
	}
}
