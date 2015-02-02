package com.company.prototype.util;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.security.Base64Encoder; 

public class UsersUtil {
	
	String algorithm = "MD5"; 
	
	public String generateMD5Password(String password){
		 
		String hashedPassword = null; 
		
		byte[] hash;
		try {
			hash = MessageDigest.getInstance(algorithm).digest(password.getBytes());
			hashedPassword = Base64Encoder.encode(hash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	    
		
		return hashedPassword;
		
	}
	

}
