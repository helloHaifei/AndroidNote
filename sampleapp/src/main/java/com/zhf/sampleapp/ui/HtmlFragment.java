package com.zhf.sampleapp.ui;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhf.sampleapp.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;
import org.w3c.dom.Text;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;

import util.ContextUtil;


/**
 * 系统的支持 字号控制 h1 -h6 <small> <big> <normal>
 * <p>
 * https://github.com/SufficientlySecure/html-textview
 * 支持表格的一些显示
 * <p>
 * https://github.com/xuyisheng/TextViewForFullHtml
 * 可以支持<font size></font> 但需要增加一些源码
 *
 * @author: Haifei
 * @data：2016-8-9
 * @blog:http://blog.csdn.net/hellohaifei
 */
public class HtmlFragment extends BaseFragment {

    final String TAG = "HtmlFragment";

    private LinearLayout mRootLayout;


    String html1 = "<html>" +
            "<body>" +
            "<h1 style=\"font-family:verdana\">A heading</h1>" +
            "<p style=\"color:#ff0000;font-size:50px;\">A paragraph.</p>" +
            "<p> <font size=\"3\" color=\"#ff0000\">颜色1\" </p>" +
            "<p> <big><big><font>编号</font></big></big> </p>" +
            "<font size=\"3\" color=\"red\">This is some text!</font>" +
            "<p style=\"font-family:arial;color:red;font-size:20px;\">A paragraph.</p>" +
            "</body>" +
            "</html>";


    String html2 = "<h1><font color='#ff0000'>我是H1标签</h1></br>" +
            "<p style=\"margin:0px auto;height:\"20px\"\"><big><font color='#ff0000'>我是big\n标签</big> </p>" +
            "<p><big><big><font color='#ff0000'>我是big+big标签</big> </big></p>" +
            "<p><small><font color='#ff0000'>我是small标签</small></p>" +
            "<p><small><small><font color='#ff0000'>我是small+small标签</small></small></p>";

    String ss = "<p><h1><font color='#ff0000'>我是H1标签</h1></p><p><big><font color='#ff0000'>我是big标签</big> </p><p><big><big><font color='#ff0000'>我是big+big标签</big> </big></p><p><small><font color='#ff0000'>我是small标签</small></p><p><small><small><font color='#ff0000'>我是small+small标签</small></small></p>";

    String sss = "<h1><font color='#ff0000'>h1标签</h1></br><p style=\"margin:0px auto;height:50px;line-height:20px;\"><big><font color='#ff0000'>ÎÒÊÇbig±êÇ©</big> </p><p style=\"margin:0px auto;height:20px;line-height:20px;\"><big><big><font color='#ff0000'>ÎÒÊÇbig+big±êÇ©</big> </big></p><p><small><font color='#ff0000'>ÎÒÊÇsmall±êÇ©</small></p><p><small><small><font color='#ff0000'>ÎÒÊÇsmall+small±êÇ©</small></small></p>";

    String html5 = "  <div style=\"text-align:center\">" +
            "     <span style=\"font-size:16px\"><font color=\"#666\">连续登录n天&nbsp;经验值</font><font color=\"#ff4d4d\">+x</font></span><br />" +
            "     <span style=\"font-size:12px;color:#999\">再有xx经验就升级喽~</span>" +
            "  </div>";
    String html6 = "<p><font size=\"6\" color=\"#666666\">连续登录n天&nbsp;经验值</font><font color=\"#ff4d4d\">+x</font></p>" +
            "<test>kkii</test><big size=5><p><font size=\"2\" color=\"#999999\">再有&nbsp;经验就升级喽~</font><font>999</font></p></big>";

    String htmlCustomFonts = "<span>8899</span><p><fonts size='20' color='#ff0000'><strike>我是标题自定义字号颜色</strike>哈哈</fonts></p>"+
            "<p><fonts size='30' color='#00ee00'>我是内容自定义字号颜色</fonts></p>";

    String htmlSpan = "<p><span style=\"font-size:16px;color:#666666;size:16\"><font>我是Span 标题</font></span><span style=\"font-size:16px;color:#ff0000;size:16\"><font>+x</font></span></p><p><span style=\"font-size:12px;color:#999999;size:12\"><font>我是Span 内容</font></span></p>";

    WebView mWebView;

    TextView mTextView;

    HtmlTextView htmlTextView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView scrollView = new ScrollView(mContext);
        mRootLayout = new LinearLayout(mContext);

        mRootLayout.setOrientation(LinearLayout.VERTICAL);

        mWebView = createWebView();
        mTextView = createView();
        htmlTextView = createHtmlView();

//        mRootLayout.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mRootLayout.addView(mWebView);
//                mRootLayout.addView(mTextView);
//
//            }
//        },2000);
        mRootLayout.addView(mWebView);
        mRootLayout.addView(mTextView);
        mRootLayout.addView(htmlTextView);

        scrollView.addView(mRootLayout);

        //Log.i(TAG, Integer.valueOf("12px") + "kk");
        return scrollView;
    }

    WebView createWebView() {

        WebView mWebView = new WebView(getContext());
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        mWebView.loadData(html5, "text/html; charset=UTF-8", null);
        return mWebView;
    }

    HtmlTextView createHtmlView() {
        htmlTextView = new HtmlTextView(getContext());
        htmlTextView.setHtml(getString(R.string.html));

        return htmlTextView;
    }

    TextView createView() {
        TextView message = new TextView(getContext());
        message.setLineSpacing(5, 0.7f);
        //message.setTextSize(12);

        Paint p = new Paint();
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName, Typeface.NORMAL);
//        p.setColor(Color.RED);
        p.setTypeface(font);

        message.getPaint().setTypeface(font);
        message.setGravity(Gravity.CENTER_HORIZONTAL);
        message.setText(Html.fromHtml(htmlSpan, null, new MyTagHandler()));

        return message;

    }


    /**
     * http://stackoverflow.com/questions/6952243/how-to-get-an-attribute-from-an-xmlreader
     * http://stackoverflow.com/questions/4044509/android-how-to-use-the-html-taghandler
     */
    class MyTagHandler implements Html.TagHandler {

        final String TAG_FONTS = "fonts";
        final String TAG_SPAN = "span";

        final String TAG_STRIKE = "STRIKE";

        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            //自定义的会走进来, 系统支持的标签 如<font> 标签不会进来
            Log.i(TAG, opening + "tag=" + tag + "  output=" + output + "xmlReader=" + xmlReader);

            if (tag.equalsIgnoreCase(TAG_FONTS)) {
                if (opening){
                    processAttributes(xmlReader);
                }
                processFonts(opening, output);
            } else if(tag.equalsIgnoreCase(TAG_STRIKE)) {
//                if (opening){
//                    processAttributes(xmlReader);
//                }
                processStrike(opening,output);
            } else if(tag.equalsIgnoreCase(TAG_SPAN)){
                if (opening){
                    processAttributes(xmlReader);
                }
                processSpan(opening, output);
            }
        }

        private void handleStartTag(String tag, Editable output, XMLReader xmlReader) {

            if (tag.equalsIgnoreCase(TAG_FONTS)) {
                startFonts(tag, output, xmlReader);
            }
        }

        private void handleEndTag(String tag, Editable output, XMLReader xmlReader) {
            if (tag.equalsIgnoreCase(TAG_FONTS)) {
                endFonts(tag, output, xmlReader);
            }
        }

        void startFonts(String tag, Editable output, XMLReader xmlReader) {

        }

        void endFonts(String tag, Editable output, XMLReader xmlReader) {

            int size  = Integer.valueOf(attributes.get("size"));
            String color = attributes.get("color");
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(size);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
            output.setSpan(sizeSpan, 0, output.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            output.setSpan(colorSpan, 0, output.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        final HashMap<String, String> attributes = new HashMap<String, String>();

        void printHashMap() {
            Log.i(TAG, "HashMap is:");
            for (String key : attributes.keySet()) {
                Log.i(TAG, key + ":" + attributes.get(key));
            }
        }

        private void processAttributes(final XMLReader xmlReader) {
            attributes.clear();
            try {
                Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
                elementField.setAccessible(true);
                Object element = elementField.get(xmlReader);
                Field attsField = element.getClass().getDeclaredField("theAtts");
                attsField.setAccessible(true);
                Object atts = attsField.get(element);
                Field dataField = atts.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                String[] data = (String[]) dataField.get(atts);
                Field lengthField = atts.getClass().getDeclaredField("length");
                lengthField.setAccessible(true);
                int len = (Integer) lengthField.get(atts);

                /**
                 * MSH: Look for supported attributes and add to hash map.
                 * This is as tight as things can get :)
                 * The data index is "just" where the keys and values are stored.
                 */
                for (int i = 0; i < len; i++) {
                    attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
                }

            } catch (Exception e) {
                Log.d(TAG, "Exception: " + e);
                e.printStackTrace();
            }
        }

        private void processFonts(boolean opening, Editable output){
            int len = output.length();
            if(opening) {
                output.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), len, len, Spannable.SPAN_MARK_MARK);
            } else {
                Object obj = getLast(output, ForegroundColorSpan.class);
                int where = output.getSpanStart(obj);

                output.removeSpan(obj);

                if (where != len) {
                    //output.setSpan(new StrikethroughSpan(), where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    int size  = Integer.valueOf(attributes.get("size"));
                    String color = attributes.get("color");
                    AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(size);
                    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
                    output.setSpan(sizeSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    output.setSpan(colorSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private void processStrike(boolean opening, Editable output) {
            int len = output.length();
            if(opening) {
                output.setSpan(new StrikethroughSpan(), len, len, Spannable.SPAN_MARK_MARK);
            } else {
                Object obj = getLast(output, StrikethroughSpan.class);
                int where = output.getSpanStart(obj);

                output.removeSpan(obj);

                if (where != len) {
                    output.setSpan(new StrikethroughSpan(), where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private void processSpan(boolean opening, Editable output){
            int len = output.length();
            if(opening) {
                output.setSpan(new AbsoluteSizeSpan(12), len, len, Spannable.SPAN_MARK_MARK);
            } else {
                Object obj = getLast(output, AbsoluteSizeSpan.class);
                int where = output.getSpanStart(obj);

                output.removeSpan(obj);

                if (where != len) {
                    //output.setSpan(new StrikethroughSpan(), where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    String style = attributes.get("style");

                    if (TextUtils.isEmpty(style)){
                        return;
                    }
                    String[] attrs = style.split(";");

                    HashMap<String,String> attrsHashMap = new HashMap<String, String>(3);

                        for (int i=0 ; i < attrs.length; i++){
                            String[] map = attrs[i].split(":");
                            if (map.length>=2) {
                                attrsHashMap.put(map[0], map[1]);
                            }
                        }

                    if (attrsHashMap.size() > 0) {
                        if(where < 0){
                            where = 0;
                        }
                        int size = Integer.valueOf(attrsHashMap.get("size"));
                        String color = attrsHashMap.get("color");
                        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(dp2Px(size));
                        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(color));
                        output.setSpan(sizeSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        output.setSpan(colorSpan, where, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }

        private Object getLast(Editable text, Class kind) {
            Object[] objs = text.getSpans(0, text.length(), kind);

            if (objs.length == 0) {
                return null;
            } else {
                for(int i = objs.length;i>0;i--) {
                    if(text.getSpanFlags(objs[i-1]) == Spannable.SPAN_MARK_MARK) {
                        return objs[i-1];
                    }
                }
                return null;
            }
        }
    }

    private DisplayMetrics mDm;
    private  DisplayMetrics getDisplayMetrics(){
        if (mDm == null){
            mDm = getContext().getResources().getDisplayMetrics();
        }
        return mDm;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px
     */
    public  int dp2Px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics()) + 0.5f);
    }
}