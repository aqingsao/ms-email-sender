package com.thoughtworks.ms.email;

import java.util.logging.Logger;

public class UserEmail {

	/** log **/
	private static Logger log = Logger.getLogger("UserEmail");
	
	/**Name user*/
	private String nameUser;
	
	/**Email user*/
	private String emailUser;
	
	/**
	 * Constructor
	 */
	public  UserEmail(){
		this.nameUser = new String();
		this.emailUser = new String();
	}
	
	/**
	 * Constructor
	 * @param nameUser
	 * @param emailUser
	 */
	public  UserEmail(String nameUser, String emailUser){
		this.nameUser = nameUser;
		this.emailUser = emailUser;
	}
	
	/**
	 * get name user
	 * @return
	 */
	public String getNameUser() {
		return nameUser;
	}
	
	/**
	 * set name user
	 * @param nameUser
	 */
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	/**
	 * get email user
	 * @return
	 */
	public String getEmailUser() {
		return emailUser;
	}
	
	/**
	 * set email user
	 * @param emailUser
	 */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	
	
	/**
	 * Compares  if the objects are equal, 
	 * if the name and email are equal to the object passed as a parameter
	 * @param object USerEmail
	 * @return
	 */
	public boolean equals(UserEmail object){
		 if (this == object) return true;
	      
		 if (!(object instanceof UserEmail)) return false;
		 
	     final UserEmail o = (UserEmail) object;
	     return (this.nameUser.equals(o.nameUser)) && (this.emailUser.equals(o.emailUser));

	}
	
	
	/**
	 * Cloned an EmailUser
	 */
	public UserEmail clone(){
		UserEmail u = null;
		
		if(this != null){
			u = new UserEmail();
			u.setEmailUser(new String(this.getEmailUser()));
			u.setNameUser(new String(this.getNameUser()));
		}
		
		return u;
	}
	
}
