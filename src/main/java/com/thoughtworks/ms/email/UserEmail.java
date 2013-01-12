package com.thoughtworks.ms.email;

import java.util.logging.Logger;

public class UserEmail {

	private static Logger LOGGER = Logger.getLogger("UserEmail");
	
	private String nameUser;
	
	private String emailUser;

	public  UserEmail(String nameUser, String emailUser){
		this.nameUser = nameUser;
		this.emailUser = emailUser;
	}
	
	public String getNameUser() {
		return nameUser;
	}

    public String getEmailUser() {
		return emailUser;
	}

    @Override
    public boolean equals(Object object){
		 if (this == object) return true;
	      
		 if (!(object instanceof UserEmail)) return false;
		 
	     final UserEmail o = (UserEmail) object;
	     return (this.nameUser.equals(o.nameUser)) && (this.emailUser.equals(o.emailUser));

	}
}
