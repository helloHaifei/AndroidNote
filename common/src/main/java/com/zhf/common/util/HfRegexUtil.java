package com.zhf.common.util;

public class HfRegexUtil {
	
	/**
	 * http 链接匹配
	 */
	public static final String REGEX_LINKS_STR = "((https?)\\:\\/\\/)" // SCHEME
					    + "([a-z0-9+!*(),;?&=\\$_.-]+(\\:[a-z0-9+!*(),;?&=\\$_.-]+)?@)?" // User and Pass
					    + "([a-z0-9-.]*)\\.([a-z]{2,4})" // Host or IP
					    + "(\\:[0-9]{2,5})?" // Port
					    + "(\\/([a-z0-9+\\$_-]\\.?)+)*\\/?" // Path
					    + "(\\?[a-z+&\\$_.-][a-z0-9;:@&%=+\\/\\$_.-]*)?" // GET Query
					    + "(#[a-z_.-][a-z0-9+\\$_.-]*)?"; // Anchor

}
