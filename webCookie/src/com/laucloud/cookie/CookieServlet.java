package com.laucloud.cookie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laucloud.utils.CookieUtils;

public class CookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = CookieUtils.findCookie(cookies, "visitCookie");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType("text/html;charset=UTF-8");
		if (cookie == null) {
			response.getWriter().print("欢迎第一次访问小窝。。。");
		} else {
			String dateStr = cookie.getValue();
			response.getWriter().print("上一次访问时间为：" + dateStr);
		}
		Cookie newCookie = new Cookie("visitCookie", new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss").format(new Date()));
		newCookie.setDomain("localhost");
		newCookie.setPath("/webCookie");
		newCookie.setMaxAge(60);
		response.addCookie(newCookie);
		// String value = "visitCookie=900000";
		// response.setHeader("Set-Cookie", value);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}