package com.notificaciones.push.util;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilMD5 {

	 private static String convertToHex(byte[] data) { 
	        StringBuffer buf = new StringBuffer();
	        for (int i = 0; i < data.length; i++) { 
	            int halfbyte = (data[i] >>> 4) & 0x0F;
	            int two_halfs = 0;
	            do { 
	                if ((0 <= halfbyte) && (halfbyte <= 9)) 
	                    buf.append((char) ('0' + halfbyte));
	                else 
	                    buf.append((char) ('a' + (halfbyte - 10)));
	                halfbyte = data[i] & 0x0F;
	            } while(two_halfs++ < 1);
	        } 
	        return buf.toString();
	    } 
	 
	public static String MD5(String text)throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	        MessageDigest md;
	        md = MessageDigest.getInstance("MD5");
	        byte[] md5hash = new byte[32];
	        md.update(text.getBytes("iso-8859-1"), 0, text.length());
	        md5hash = md.digest();
	        return convertToHex(md5hash);
	    }
	    
	public static String getMD5Spring(String cadena) throws NoSuchAlgorithmException{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(cadena.getBytes(),0, cadena.length());
			String hashedCadena = new BigInteger(1,messageDigest.digest()).toString(16);
			if (hashedCadena.length() < 32) {
				hashedCadena = "0" + hashedCadena; 
			}
			return hashedCadena;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String text = "admin";
		System.out.println(UtilMD5.MD5(text));
	}
}
