package com.anhvu.utils;

import org.apache.commons.text.StringEscapeUtils;

public class UserInputSanitizer {
	
	public static String sanitizer(String input) {
		return StringEscapeUtils.escapeHtml4(input);
	}
}
