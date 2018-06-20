package com.notiificaciones.push.service;

import java.util.List;
import java.util.Map;

import com.notiificaciones.push.domain.mysql.UsuarioSubscrito;
import com.notiificaciones.push.vo.Mensaje;

public interface WebPushService {

	public UsuarioSubscrito registroUsuarioSubscrito(UsuarioSubscrito usuarioSubscrito)throws Exception;
	
	public List<UsuarioSubscrito> findUsuariosSubscritos(UsuarioSubscrito usuarioSubscrito) throws Exception;
	
	public Map<String, Object> sendSingleNotificacion(Mensaje mensaje) throws Exception;
	
	public Map<String, Object> sendNotificacionMasiva(Mensaje mensaje) throws Exception;
	
}
