package com.anhvu.utils;

import javax.servlet.http.HttpServletRequest;

public class URL {
	public static String getURL(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		return url.replace(request.getServletPath(), "");
	}

}
