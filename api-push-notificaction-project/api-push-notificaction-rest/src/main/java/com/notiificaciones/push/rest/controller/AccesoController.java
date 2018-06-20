package com.notiificaciones.push.rest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notificaciones.push.util.Constante;
import com.notiificaciones.push.domain.mysql.Usuario;
import com.notiificaciones.push.service.UsuarioService;

@Controller
public class AccesoController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/login")
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView("public/login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/autenticar" , method = RequestMethod.POST)
	public void autenticar(String username,String password , HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		String view = "admin";
		HttpSession sesion = request.getSession();
		
		Usuario usuario = (Usuario) sesion.getAttribute(Constante.NAME_LOGIN_SESION);
		
		if(usuario == null){
			
			if(StringUtils.isBlank(username)){
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("mensajeError", "Debe ingresar el usuario.");
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
			
			if(StringUtils.isBlank(password)){
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("mensajeError", "Debe ingresar su contraseña.");
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
			
			
			try {
				usuario = usuarioService.autenticar(username, password);
				
				if(usuario == null){
					request.setAttribute("mensajeError", "Los Datos Ingresados son incorrectos.");
					request.setAttribute("username", username);
					request.setAttribute("password", password);
					request.getRequestDispatcher("/login").forward(request, response);
					return;
				}else{
									 
					sesion.setAttribute(Constante.NAME_LOGIN_SESION, usuario);
					response.sendRedirect(view);
				}
				
			}
			catch (Exception e) {
				request.setAttribute("username", username);
				request.setAttribute("password", password);
				request.setAttribute("mensajeError", e.getMessage());
				request.getRequestDispatcher("/login").forward(request, response);
				return;
			}
			
		}else{
			String context = request.getContextPath();
			response.sendRedirect(context + "/admin");
			return;
		}
		
	}
	
	@RequestMapping(value = "/logout")
	public void CerrarSesion( HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		sesion.invalidate();	
		String context = request.getContextPath();
		response.sendRedirect(context + "/login");
	}
	
}
