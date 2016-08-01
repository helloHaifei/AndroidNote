package com.zhf.sampleapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhf.sampleapp.R;

/**
 * 	TextView span例子
 * 
 * @author: Haifei
 * @data：2014-3-25
 * @blog:http://blog.csdn.net/hellohaifei
 * 
 */
public class TextViewSpanFragment extends BaseFragment {
	private TextView mTextView;

	SpannableStringBuilder spanBuilder;
	String contentText;

	

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mTextView = new TextView(mContext);
		initSpan();
		mTextView.setText(spanBuilder);

		mTextView.setTextSize(25);
		///mTextView.setBackgroundColor(R.drawable.textview_span_color);
		// 设置TextView中的超链接可点击
		mTextView.setMovementMethod(LinkMovementMethod.getInstance());
		
		
		return mTextView;
	}
	
	private void initSpan() {
		String link = "链接\n";
		String email = "邮件\n";
		String tel = "电话\n";
		String sms = "短信\n";
		String mms = "彩信\n";
		String map = "地图\n";
		String strikethrough = "删除线\n";
		String relativeSize1 = "缩小字体一半\n";
		String relativeSize2 = "放大字体2倍\n";
		String scaleX1 = "缩小\n";
		String scaleX2 = "放大\n";
		String subscript = "下标";
		String superscript = "上标\n";
		String foreground = "前景色\n";
		String background = "背景色\n";
		String underline = "下划线\n";
		String italic = "加粗斜体\n";
		String weiboName = "@hellohaifei\n";
		String owner = "图片\n";
		String end = "\n结束";

		contentText = link + email + tel + sms + mms + map + strikethrough
				+ relativeSize1 + relativeSize2 + scaleX1 + scaleX2 + subscript
				+ superscript + foreground + background + underline + italic
				+ weiboName + owner + end;
		spanBuilder = new SpannableStringBuilder(contentText);

		addSpan(link, new URLSpan("http://www.baidu.com"));// 超链接
		addSpan(email, new URLSpan("mailto:webmaster@google.com")); // 邮件
		addSpan(tel, new URLSpan("tel:4000707366")); // 电话
		addSpan(sms, new URLSpan("sms:4000707366")); // 短信
		addSpan(mms, new URLSpan("mms:4000707366")); // 彩信
		addSpan(map, new URLSpan("geo:4000707366")); // 地图
		addSpan(strikethrough, new StrikethroughSpan()); // 删除线
		addSpan(relativeSize1, new RelativeSizeSpan(0.5f)); //
		addSpan(relativeSize2, new RelativeSizeSpan(2.0f)); //
		addSpan(scaleX1, new ScaleXSpan(0.5f)); //
		addSpan(scaleX2, new ScaleXSpan(2.0f)); //
		addSpan(subscript, new SubscriptSpan()); // 下标
		addSpan(superscript, new SuperscriptSpan()); // 上标
		addSpan(foreground, new ForegroundColorSpan(Color.YELLOW));
		addSpan(background, new BackgroundColorSpan(Color.RED));
		addSpan(underline, new UnderlineSpan());
		addSpan(italic, new StyleSpan(android.graphics.Typeface.BOLD_ITALIC));
		addSpan(weiboName, new WeiboAtClickSpan());
		addSpan(owner, new ImageSpan(mContext, R.drawable.ic_launcher));// 图片

	}

	private void addSpan(String text, CharacterStyle span) {

		int start = contentText.indexOf(text);
		int end = start + text.length();
		spanBuilder.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
	}

	class WeiboAtClickSpan extends ClickableSpan {
		@Override
		public void onClick(View arg0) {
			// 响应文字点击事件
			Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			// 设置没有下划线
			ds.setUnderlineText(false);
			// 设置颜色高亮
			ds.setARGB(255, 255, 00, 00);
			ds.bgColor = 0xabababab;
			
		}
	}

}