package com.company.prototype.util;

import javax.faces.context.FacesContext;

public class ControllerUtils {
	
	public static String getSessionUser(FacesContext facesContext){
		String username = facesContext.getExternalContext().getRemoteUser();
		return username;
	}
	

}
