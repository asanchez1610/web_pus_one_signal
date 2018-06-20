package com.notiificaciones.push.service.impl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.notificaciones.push.util.Constante;
import com.notiificaciones.push.dao.mysql.UsuarioSubscritoMapper;
import com.notiificaciones.push.domain.mysql.UsuarioSubscrito;
import com.notiificaciones.push.domain.mysql.UsuarioSubscritoCriteria;
import com.notiificaciones.push.service.WebPushService;
import com.notiificaciones.push.vo.Mensaje;

@Service
public class WebPushServiceImpl implements WebPushService {

	@Autowired
	private UsuarioSubscritoMapper usuarioSubscritoMapper;
	
	private UsuarioSubscrito getUsuarioSubscritoIfExists(String userId)throws Exception{
		
		UsuarioSubscritoCriteria usc = new UsuarioSubscritoCriteria();
		usc.createCriteria().andPlayerIdEqualTo(userId);
		
		List<UsuarioSubscrito> list = usuarioSubscritoMapper.selectByExample(usc);
		
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
		
	}

	@Transactional
	public UsuarioSubscrito registroUsuarioSubscrito(UsuarioSubscrito usuarioSubscrito) throws Exception {
		
		UsuarioSubscrito eval = this.getUsuarioSubscritoIfExists(usuarioSubscrito.getPlayerId());
		
		if(eval == null) {
			usuarioSubscrito.setSusbscrito(Constante.ESTADO_AFIRMATIVO);
			usuarioSubscrito.setRegistro(new Date());
			usuarioSubscritoMapper.insertSelective(usuarioSubscrito);
			return usuarioSubscrito;
		}
		
		return eval;
		
	}

	public List<UsuarioSubscrito> findUsuariosSubscritos(UsuarioSubscrito usuarioSubscrito) throws Exception {
		
		UsuarioSubscritoCriteria usc = new UsuarioSubscritoCriteria();
		com.notiificaciones.push.domain.mysql.UsuarioSubscritoCriteria.Criteria criteria;
		criteria = usc.createCriteria();
		
		if(usuarioSubscrito.getPlayerId() != null) {
			criteria.andPlayerIdEqualTo(usuarioSubscrito.getPlayerId());
		}
		
		if(usuarioSubscrito.getNombre() != null) {
			criteria.andNombreLikeInsensitive(usuarioSubscrito.getNombre());
		}
		
		if(usuarioSubscrito.getSusbscrito() != null) {
			criteria.andSusbscritoEqualTo(usuarioSubscrito.getSusbscrito());
		}
		
		return usuarioSubscritoMapper.selectByExample(usc);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> sendSingleNotificacion(Mensaje mensaje) throws Exception {
		
		   String jsonResponse;
		   
		   URL url = new URL(Constante.END_POINT_ONESIGNAL);
		   HttpURLConnection con = (HttpURLConnection)url.openConnection();
		   con.setUseCaches(false);
		   con.setDoOutput(true);
		   con.setDoInput(true);

		   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		   con.setRequestProperty("Authorization", "Basic "+Constante.REST_KEY_ONESIGNAL);
		   con.setRequestMethod("POST");

		   String strJsonBody = "{"
		                      +   "\"app_id\": \""+Constante.APP_KEY_ONESIGNAL+"\","
		                      +   "\"include_player_ids\": [\""+mensaje.getPlayerId()+"\"],"
		                      +   "\"contents\": {\"en\": \""+mensaje.getMensaje()+"\"},"
		                      +   "\"headings\": {\"en\" : \""+mensaje.getTitulo()+"\"}"
		                      + "}";
		         
		   
		   System.out.println("strJsonBody:\n" + strJsonBody);

		   byte[] sendBytes = strJsonBody.getBytes("UTF-8");
		   con.setFixedLengthStreamingMode(sendBytes.length);

		   OutputStream outputStream = con.getOutputStream();
		   outputStream.write(sendBytes);

		   int httpResponse = con.getResponseCode();
		   System.out.println("httpResponse: " + httpResponse);

		   if (  httpResponse >= HttpURLConnection.HTTP_OK
		      && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
		      Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
		      scanner.close();
		   }
		   else {
		      Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
		      scanner.close();
		   }

		   Gson gson = new Gson();
		   
		   Map<String, Object> response = gson.fromJson(jsonResponse, Map.class);
		   
		return response;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> sendNotificacionMasiva(Mensaje mensaje) throws Exception {
		
		 String jsonResponse;
		   
		   URL url = new URL("https://onesignal.com/api/v1/notifications");
		   HttpURLConnection con = (HttpURLConnection)url.openConnection();
		   con.setUseCaches(false);
		   con.setDoOutput(true);
		   con.setDoInput(true);

		   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		   con.setRequestProperty("Authorization", "Basic "+Constante.REST_KEY_ONESIGNAL);
		   con.setRequestMethod("POST");

		   String strJsonBody = "{"
		                      +   "\"app_id\": \""+Constante.APP_KEY_ONESIGNAL+"\","
		                      +   "\"included_segments\": [\"All\"],"
		                      +   "\"contents\": {\"en\": \""+mensaje.getMensaje()+"\"},"
		                      +   "\"headings\": {\"en\" : \""+mensaje.getTitulo()+"\"}"
		                      + "}";
		         
		   
		   System.out.println("strJsonBody:\n" + strJsonBody);

		   byte[] sendBytes = strJsonBody.getBytes("UTF-8");
		   con.setFixedLengthStreamingMode(sendBytes.length);

		   OutputStream outputStream = con.getOutputStream();
		   outputStream.write(sendBytes);

		   int httpResponse = con.getResponseCode();
		   System.out.println("httpResponse: " + httpResponse);

		   if (  httpResponse >= HttpURLConnection.HTTP_OK
		      && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
		      Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
		      scanner.close();
		   }
		   else {
		      Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
		      scanner.close();
		   }
		
		   Gson gson = new Gson();
		   
		Map<String, Object> response = gson.fromJson(jsonResponse, Map.class);
		   
		return response;
	}

}
