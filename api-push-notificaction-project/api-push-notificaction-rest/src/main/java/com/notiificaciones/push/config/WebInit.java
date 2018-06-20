package com.notiificaciones.push.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.notiificaciones.push.filter.CORSFilter;
import com.notiificaciones.push.filter.FilterAutenticacion;

public class WebInit implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(WebConfig.class);
		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servletContext.addListener(SessionListener.class);
		servlet.setLoadOnStartup(1);
		filterRegistration(servletContext);
	}

	private void filterRegistration(final ServletContext servletContext) {
		FilterRegistration filterRegistration = servletContext.addFilter("CORSFilter", CORSFilter.class);
		filterRegistration.addMappingForUrlPatterns(null, false, new String[] { "/*" });
		
		FilterRegistration filterAutenticacion = servletContext.addFilter("FilterAutenticacion",FilterAutenticacion.class);
		filterAutenticacion.setInitParameter("encoding", "UTF-8");
		filterAutenticacion.setInitParameter("forceEncoding", "true");
		filterAutenticacion.addMappingForUrlPatterns(null, false, new String[] { "/admin/*" });
		
	}

}
