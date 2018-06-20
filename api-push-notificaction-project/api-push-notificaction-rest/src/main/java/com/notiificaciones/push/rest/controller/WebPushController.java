package com.notiificaciones.push.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.notiificaciones.push.domain.mysql.UsuarioSubscrito;
import com.notiificaciones.push.service.WebPushService;
import com.notiificaciones.push.vo.Mensaje;

@Controller
@RequestMapping(value = "/admin")
public class WebPushController {
	
	@Autowired
	private WebPushService webPushService;
	
	@RequestMapping
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("private/main");
		return modelAndView;
	}
	
	@RequestMapping(value = "/subscriptores/listar")
	public @ResponseBody Map<String, ? extends Object> anunciosListar(UsuarioSubscrito usuarioSubscrito) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.put("data", webPushService.findUsuariosSubscritos(usuarioSubscrito));
			data.put("success", Boolean.TRUE);
		}catch (Exception e) {
			data.put("success", Boolean.FALSE);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/notificacions/single")
	public @ResponseBody Map<String, ? extends Object> notificacionSingle(Mensaje mensaje) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.putAll(webPushService.sendSingleNotificacion(mensaje));
			data.put("success", Boolean.TRUE);
		}catch (Exception e) {
			data.put("success", Boolean.FALSE);
			e.printStackTrace();
		}
		return data;
	}
	
	@RequestMapping(value = "/notificacions/all")
	public @ResponseBody Map<String, ? extends Object> notificacionAll(Mensaje mensaje) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			data.putAll(webPushService.sendNotificacionMasiva(mensaje));
			data.put("success", Boolean.TRUE);
		}catch (Exception e) {
			data.put("success", Boolean.FALSE);
			e.printStackTrace();
		}
		return data;
	}
	
}
