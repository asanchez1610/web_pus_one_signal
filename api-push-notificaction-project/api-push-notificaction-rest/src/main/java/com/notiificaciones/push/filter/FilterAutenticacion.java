package com.notiificaciones.push.filter;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.notificaciones.push.util.Constante;

public class FilterAutenticacion implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpRequest.setCharacterEncoding("UTF-8");

		HttpSession sesion = httpRequest.getSession();
		String context = httpRequest.getContextPath();

		boolean isAjax = (httpRequest.getHeader("x-requested-with") != null
				&& httpRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"));


		if (sesion.getAttribute(Constante.NAME_LOGIN_SESION) == null) {
			if (isAjax) {

				httpResponse.setContentType("text/json; charset=UTF-8");
				httpResponse.setStatus(403);
				PrintWriter out = httpResponse.getWriter();
				String json = "{ autenticationFail : true , error : \"Error en la verificación de sus credenciales.\" }";

				out.write(json);
				out.flush();
				out.close();

			}else {
				httpResponse.sendRedirect(context + "/login");
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}

		

	}

	@Override
	public void destroy() {
	}

}
