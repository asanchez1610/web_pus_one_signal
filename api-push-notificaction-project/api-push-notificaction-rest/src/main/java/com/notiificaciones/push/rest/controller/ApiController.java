package com.notiificaciones.push.rest.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.notiificaciones.push.domain.mysql.UsuarioSubscrito;
import com.notiificaciones.push.service.WebPushService;

@RestController
@RequestMapping(value = "/api/")
@SuppressWarnings({ "rawtypes", "unchecked"})
public class ApiController {

	@Autowired
	private WebPushService webPushService;
	
	
	@PostMapping(value = "subscripcion" , consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity  findOrCreate(@RequestBody UsuarioSubscrito usuarioSubscrito){
		
		Map<String, Object> data = new HashMap<String,Object>();
		try {
			data.put("subscripcion", webPushService.registroUsuarioSubscrito(usuarioSubscrito));
			data.put("success", Boolean.TRUE);
			return new ResponseEntity(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			data.put("success", Boolean.FALSE);
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@GetMapping(value = "subscripcion" )
	public ResponseEntity  findSubscripciones(UsuarioSubscrito usuarioSubscrito){
		
		Map<String, Object> data = new HashMap<String,Object>();
		try {
			
			data.put("data", webPushService.findUsuariosSubscritos(usuarioSubscrito));
			return new ResponseEntity(data,HttpStatus.OK);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			data.put("message", e.getMessage());
			return new ResponseEntity(data,HttpStatus.EXPECTATION_FAILED);
		
		}
		
	}
	
}
