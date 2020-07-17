package com.example.common.handler;

import com.example.common.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * @escription 全局拦截字符异常处理
 * @author zwk
 * @version 2020年7月16日 17点36分
 */
public class DefenseInterceptor implements HandlerInterceptor {


	private static Logger logger = LoggerFactory.getLogger(DefenseInterceptor.class);
	public static final String origins = Config.getInstance().getProperty("origins");

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		logger.debug("进入特殊字符拦截器");
		if (request.getMethod().equalsIgnoreCase("post")) {
			request.setCharacterEncoding("utf-8");
		} else {
			@SuppressWarnings("rawtypes")
			Iterator its = request.getParameterMap().values().iterator();
			while (its.hasNext()) {
				String[] params = (String[]) its.next();
				int len = params.length;
				for (int i = 0; i < len; i++) {
					params[i] = new String(params[i].getBytes("utf-8"), "utf-8");
				}
			}
		}
		// 过滤客户端提交表单中特殊字符
		@SuppressWarnings("rawtypes")
		Iterator its = request.getParameterMap().values().iterator();
		while (its.hasNext()) {
			String[] params = (String[]) its.next();
			for (int i = 0; i < params.length; i++) {
				boolean validateTool = DefenseUtil.validateTool(params[i]);
				if (!validateTool) {
					response.reset();
					response.setCharacterEncoding("utf-8");
					response.setHeader("Access-Control-Allow-Origin", origins);
					response.setHeader("Access-Control-Allow-Credentials", "true");
					PrintWriter out = response.getWriter();
//					out.println("ERRDATA"+params[i]);
					out.println("{\"ERRDATA\":\""+params[i]+"\"}");
					out.close();
					return false;
				}
			}

		}
		response.setCharacterEncoding("utf-8");
		return true;
	}
}
