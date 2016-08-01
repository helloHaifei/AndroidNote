package com.zhf.common.view;



import java.io.InputStream;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.ReplacementSpan;
import android.util.Log;

/**
 * TextView 设置行间距后，用来校正图片显示的位置
 */
public class FaceDrawableSpan extends ReplacementSpan {
    private static final String TAG = "MyDynamicDrawableSpan";
    
    private Drawable mDrawable;
    private Uri mContentUri;
    private int mResourceId;
    private Context mContext;
    
    private int mPadding = 0;

    /**
     * @param verticalAlignment one of {@link DynamicDrawableSpan#ALIGN_BOTTOM} or
     * {@link DynamicDrawableSpan#ALIGN_BASELINE}.
     */
    public FaceDrawableSpan(Context context, int resourceId ) {
        
        mContext = context;
        mResourceId = resourceId;
    }

    public FaceDrawableSpan(Context context, int resourceId ,int padding) {
        
        mContext = context;
        mResourceId = resourceId;
        mPadding = padding;
    }
    /**
     * Your subclass must implement this method to provide the bitmap   
     * to be drawn.  The dimensions of the bitmap must be the same
     * from each call to the next.
     */
    public Drawable getDrawable() {
        Drawable drawable = null;
        
        if (mDrawable != null) {
            drawable = mDrawable;
        } else  if (mContentUri != null) {
            Bitmap bitmap = null;
            try {
                InputStream is = mContext.getContentResolver().openInputStream(
                        mContentUri);
                bitmap = BitmapFactory.decodeStream(is);
                drawable = new BitmapDrawable(mContext.getResources(), bitmap);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                is.close();
            } catch (Exception e) {
                Log.e("sms", "Failed to loaded content " + mContentUri, e);
            }
        } else {
            try {
                drawable = mContext.getResources().getDrawable(mResourceId);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
            } catch (Exception e) {
                Log.e("sms", "Unable to find resource: " + mResourceId);
            }                
        }

        return drawable;
    }

    @Override
    public int getSize(Paint paint, CharSequence text,
                         int start, int end,
                         FontMetricsInt fm) {
        Drawable d = getCachedDrawable();
        Rect rect = d.getBounds();

        if (fm != null) {
            fm.ascent = -rect.bottom; 
            fm.descent = 0; 

            fm.top = fm.ascent;
            fm.bottom = 0;
        }

        return rect.right;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text,
                     int start, int end, float x, 
                     int top, int y, int bottom, Paint paint) {
        Drawable b = getCachedDrawable();
        canvas.save();
        
        int transY = bottom - b.getBounds().bottom;
        /*if (mVerticalAlignment == ALIGN_BASELINE) {
        	 transY -= paint.getFontMetricsInt().descent;
        }*/
        //zhanghaifei 2014-7-21
        FontMetricsInt metrics = paint.getFontMetricsInt();
        Log.d(TAG, metrics.toString());
        
        transY -= (mPadding);
        
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

    private Drawable getCachedDrawable() {
        WeakReference<Drawable> wr = mDrawableRef;
        Drawable d = null;

        if (wr != null)
            d = wr.get();

        if (d == null) {
            d = getDrawable();
            mDrawableRef = new WeakReference<Drawable>(d);
        }

        return d;
    }

    private WeakReference<Drawable> mDrawableRef;
}

