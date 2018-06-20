package com.notiificaciones.push.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@ComponentScan(basePackages = { "com.notiificaciones.push" })
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Bean
	 public UrlBasedViewResolver setupViewResolver() {
		
		 UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		 resolver.setPrefix("/WEB-INF/views/");
		 resolver.setSuffix(".jsp");
		 resolver.setViewClass(JstlView.class);
		 return resolver;
	 
	 }
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
	    registry.addResourceHandler("/font/**").addResourceLocations("/static/font/");
	    registry.addResourceHandler("/images/**").addResourceLocations("/static/images/");
	    registry.addResourceHandler("/jquery/**").addResourceLocations("/static/jquery/");
	    registry.addResourceHandler("/materialize/**").addResourceLocations("/static/materialize/");
	}
	
}
