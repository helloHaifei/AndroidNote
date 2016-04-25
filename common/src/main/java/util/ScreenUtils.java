package util;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 屏幕分辨率转换工具
 */
public class ScreenUtils {

	/**
	 * 获取标题栏的高度
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		try {
			int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
			if (resourceId > 0) {
				result = context.getResources().getDimensionPixelSize(resourceId);
			}
		}catch (Exception e){
		}
		return result;
	}

//	/**
//	 * 获取标题栏的高度
//	 *
//	 * @param activity
//	 * @return > 0 success; <= 0 fail
//	 */
//	public static int getStatusHeight(Activity activity) {
//		int statusHeight = 0;
//		int resourceId = activity.getResources().getIdentifier(
//				"status_bar_height", "dimen", "android");
//		if (resourceId > 0) {
//			statusHeight = activity.getResources().getDimensionPixelSize(
//					resourceId);
//		}
//		if(statusHeight!=0)
//			return statusHeight;
//		Rect localRect = new Rect();
//		activity.getWindow().getDecorView()
//				.getWindowVisibleDisplayFrame(localRect);
//		statusHeight = localRect.top;
//		if (0 == statusHeight) {
//			Class<?> localClass;
//			try {
//				localClass = Class.forName("com.android.internal.R$dimen");
//				Object localObject = localClass.newInstance();
//				int i5 = Integer.parseInt(localClass
//						.getField("status_bar_height").get(localObject)
//						.toString());
//				statusHeight = activity.getResources()
//						.getDimensionPixelSize(i5);
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (NoSuchFieldException e) {
//				e.printStackTrace();
//			}
//		}
//		return statusHeight;
//	}

//    public static float dpToPx(Context context, float dp) {
//        if (context == null) {
//            return -1;
//        }
//        return dp * context.getResources().getDisplayMetrics().density;
//    }

	/**
	 * @deprecated user{@link #dp2Px(int)} instead.
	 */
	@Deprecated
    public static int dpToPxInt(Context context, int dp) {
        return dp2Px(dp);
    }

//    public static int sp2px(Context context, int spValue) {
//        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (spValue * fontScale + 0.5f);
//    }
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dp(int pxValue) {
        final float scale = getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);  
    }
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
//    public static float px2dip(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return pxValue / scale + 0.5f;
//    }

	private static DisplayMetrics mDm;
	private static DisplayMetrics getDisplayMetrics(){
		if (mDm == null){
			mDm = ContextUtil.getApplicationContext().getResources().getDisplayMetrics();
		}
		return mDm;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px
	 */
	public static int dp2Px(float dp) {
		return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics()) + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 sp(字体) 的单位 转成为 px
	 */
	public static int sp2Px(int spValue) {
		final float fontScale = getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**当前屏幕高度*/
	public static int getCurrentScreenHeight(){
		return getDisplayMetrics().heightPixels;
	}

	/**当前屏幕宽度*/
	public static int getCurrentScreenWidth(){
		return getDisplayMetrics().widthPixels;
	}

	public static int getDimenSP(@DimenRes int spRes){
		return ContextUtil.getApplicationContext().getResources().getDimensionPixelSize(spRes);
	}
}
