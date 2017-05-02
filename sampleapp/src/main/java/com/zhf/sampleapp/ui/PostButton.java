package com.zhf.sampleapp.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zhf.sampleapp.R;

/**
 * Created by zhf on 16/8/10.
 */
public class PostButton extends ImageButton implements View.OnClickListener {

    public static final int ITEM_DYNAMIC = 1, ITEM_ARTICLE = 2, ITEM_QUESTION = 3;

    private PopupWindow mPopupWindow;
    private RelativeLayout mPopupRootView;
    private RelativeLayout mPopupContentLayout;
    private ImageButton mCloseBtn;

    private OnClickItemListener mOnClickItemListener;

    private int duration = 200;

    private int mBaseWidth, mBaseHeight;

    public interface OnClickItemListener{
        public void onItemClick(int position);
    }

    public void setOnClickItemListener(OnClickItemListener listener){
        this.mOnClickItemListener = listener;
    }

    public PostButton(Context context) {
        this(context, null);
    }

    public PostButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public boolean isShowPop(){
        if (mPopupWindow != null){
            return mPopupWindow.isShowing();
        }else{
            return false;
        }
    }
    private void init(){
        setOnClickListener(this);
        setBackgroundResource(R.drawable.btn_send_posts_bg_selector);
        setImageResource(R.drawable.btn_send_add_posts_icon_normal);

        //view加载完成时回调
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                WindowManager wm = (WindowManager) getContext()
                        .getSystemService(Context.WINDOW_SERVICE);

                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();

                View view = PostButton.this;
                Rect rect = new Rect();
                if (view.getGlobalVisibleRect(rect)) {
                    mBaseWidth = width - rect.right;
                    mBaseHeight = height - rect.bottom;
                }

            }
        });
    }

    private void showPopupWindow(View parent) {

        initPopView();

        mPopupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inAnimation();
            }
        },10);
//        inAnimation();
    }

    public void closePopupWindow() {
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
        }
    }

    public void closePopupWindowWidthAnimation() {
        outAnimation();
    }

    private void inAnimation(){

//        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.popmenu_right_bottom_in);
//        mPopupContentLayout.startAnimation(animation);


//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1f);

//        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX",mPopupContentLayout.getWidth() - 300, 0f);
//        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY",mPopupContentLayout.getHeight() - 200, 0f);
//        ObjectAnimator.ofPropertyValuesHolder(mPopupContentLayout,  translationX, translationY).setDuration(5000).start();

//        mPopupContentLayout.setPivotY(0.0f);
        int offset = dip2px(15);
        measureView(mPopupContentLayout);
        mPopupContentLayout.setPivotX(mPopupContentLayout.getWidth() - offset);
        mPopupContentLayout.setPivotY(mPopupContentLayout.getHeight() - offset);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",0.5f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",0.5f,1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mPopupContentLayout,  scaleX, scaleY).setDuration(duration);
        ///animator.setInterpolator(new AccelerateInterpolator());
        animator.start();


        ObjectAnimator.ofFloat(mPopupRootView,"alpha",0.1f,1.0f).setDuration(duration).start();

        ObjectAnimator.ofFloat(mCloseBtn,"rotation", -45).setDuration(duration).start();
    }

    private void outAnimation(){
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1f, 0.5f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1f,0.5f);
        ObjectAnimator.ofPropertyValuesHolder(mPopupContentLayout,  scaleX, scaleY).setDuration(duration).start();


        ObjectAnimator.ofFloat(mPopupRootView,"alpha",1f,0.1f).setDuration(duration).start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mCloseBtn,"rotation", 45).setDuration(duration);

        objectAnimator.addListener(new Animator.AnimatorListener(){

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                closePopupWindow();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();
    }



    private void initPopView(){
        if(mPopupRootView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            mPopupRootView = (RelativeLayout)inflater.inflate(R.layout.pop_menu_layout,null);

            mPopupContentLayout = (RelativeLayout) mPopupRootView.findViewById(R.id.pop_menu_layout);

            mCloseBtn = (ImageButton) mPopupRootView.findViewById(R.id.pop_menu_close);

            mPopupRootView.setOnClickListener(this);
            mCloseBtn.setOnClickListener(this);
            mPopupRootView.findViewById(R.id.pop_menu_dynamic).setOnClickListener(this);
            mPopupRootView.findViewById(R.id.pop_menu_article).setOnClickListener(this);
            mPopupRootView.findViewById(R.id.pop_menu_question).setOnClickListener(this);

            Button view = (Button) mPopupRootView.findViewById(R.id.pop_menu_base);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
            params.width = mBaseWidth;
            params.height = mBaseHeight;
            view.setLayoutParams(params);

        }

        if(mPopupWindow == null){
            mPopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//            mPostBtn.setBackgroundResource(R.drawable.tab_send_close);
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
//                    mPostBtn.setBackgroundResource(R.drawable.tab_send_btn);
                }
            });
            // 使其聚集
            mPopupWindow.setFocusable(true);
            // 设置允许在外点击消失
            mPopupWindow.setOutsideTouchable(true);
            // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

            mPopupWindow.setContentView(mPopupRootView);

        }
    }

    @Override
    public void onClick(View v) {
        if (v == this){
            showPopupWindow(v);
            return;
        }else if(v == mPopupRootView){
            closePopupWindowWidthAnimation();
            return;
        }
        switch (v.getId()){
            case R.id.pop_menu_close:
                closePopupWindowWidthAnimation();
                break;
            case R.id.pop_menu_dynamic:
                closePopupWindowWidthAnimation();
                if (mOnClickItemListener!=null){
                    getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnClickItemListener.onItemClick(ITEM_DYNAMIC);
                        }
                    },duration);
                }
                break;
            case R.id.pop_menu_article:
                closePopupWindowWidthAnimation();
                if (mOnClickItemListener!=null){
                    getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnClickItemListener.onItemClick(ITEM_ARTICLE);
                        }
                    },duration);
                }

                break;
            case R.id.pop_menu_question:
                closePopupWindowWidthAnimation();
                if (mOnClickItemListener!=null){
                    getHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mOnClickItemListener.onItemClick(ITEM_QUESTION);
                        }
                    },duration);
                }
                break;
        }
    }

    public void measureView(View v){
        if(v == null){
            return;
        }
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);
    }

    public int dip2px(float dipValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
