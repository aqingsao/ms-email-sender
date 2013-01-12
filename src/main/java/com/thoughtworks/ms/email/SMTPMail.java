package com.thoughtworks.ms.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.internet.InternetAddress;

public class SMTPMail {
		
	  /**log*/
	  private Logger log = Logger.getLogger("SMTPMail");

	  /**toAddresses*/ 
	  protected Map toAddresses = new HashMap();
	  
	  /**ccAddresses*/
	  protected Map ccAddresses = new HashMap();
	  
	  /**bccAddresses*/
	  protected Map bccAddresses = new HashMap();
	  
	  /**replyToAddresses*/
	  protected Map replyToAddresses = new HashMap();

	  /**fromName*/
	  protected String fromName = null;
	  
	  /**fromAddress*/
	  protected String fromAddress = null;
	  
	  /**subject*/
	  protected String subject = null;

	  /**messageText*/
	  protected String messageText = new String();

	  /** List  of attachments */
	  private List<SMTPAttachment> attachments = new ArrayList<SMTPAttachment>();

	  /**
	   * Constructor
	   * @param fromName
	   * @param fromAddress
	   * @param subject
	   */
	  public SMTPMail(String fromName, String fromAddress, String subject) {
	    this.fromName = fromName;
	    this.fromAddress = fromAddress;
	    this.subject = subject;
	  }

	  /**
	   * get from
	   * @return
	   * @throws Exception
	   */
	  public InternetAddress getFrom() throws Exception{
	    return new InternetAddress(fromAddress, fromName);
	  }

	  /**
	   * get subject
	   * @return
	   */
	  public String getSubject(){
	    return subject;
	  }

	  /**
	   * get Message text
	   * @return
	   */
	  public String getMessageText(){
	    return messageText;
	  }


	  /**
	   * set Message text
	   * @param messageText
	   */
	  public void setMessageText(String messageText) {
	    this.messageText = messageText;
	  }

	  /**
	   * add an address
	   * @param name
	   * @param address
	   */
	  public void addToAddress(String name, String address) {
	    toAddresses.put(name, address);
	  }

	  /**
	   * 
	   * @param name
	   * @param address
	   */
	  public void addCcAddress(String name, String address) {
	    ccAddresses.put(name, address);
	  }

	  /**
	   * 
	   * @param name
	   * @param address
	   */
	  public void addBccAddress(String name, String address) {
	    bccAddresses.put(name, address);
	  }

	  /**
	   * 
	   * @param name
	   * @param address
	   */
	  public void addReplyToAddress(String name, String address) {
	    replyToAddresses.put(name, address);
	  }

	  /**
	   * 
	   * @return
	   * @throws Exception
	   */
	  public InternetAddress[] getToAddresses()  throws Exception{
	    return getAddresses(toAddresses);
	  }

	  /**
	   * 
	   * @return
	   * @throws Exception
	   */
	  public InternetAddress[] getCcAddresses()  throws Exception{
	    return getAddresses(ccAddresses);
	  }

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	  public InternetAddress[] getBccAddresses()  throws Exception{
	    return getAddresses(bccAddresses);
	  }

	  /**
	   * 
	   * @return
	   * @throws Exception
	   */
	  public InternetAddress[] getReplyToAddresses()  throws Exception{
	    return getAddresses(replyToAddresses);
	  }

	  /**
	   * 
	   * @param addresses
	   * @return
	   * @throws Exception
	   */
	  private InternetAddress[] getAddresses(Map addresses)
	    throws Exception {
	    InternetAddress address = null;
	    ArrayList list = new ArrayList();
	    String name = null;
	    String email = null;
	    Iterator i = addresses.keySet().iterator();

	    while (i.hasNext()) {
	      name = (String) i.next();
	      email = (String) addresses.get(name);

	      try{
	    	  list.add(new InternetAddress(email, name));
	      }
	      catch (Exception ex) {
	        log.info("E-mail  not valid " + ex);
	      }
	    }

	    if (list.isEmpty()) {
	      return null;
	    }

	    InternetAddress all[] = new InternetAddress[list.size()];
	    all = (InternetAddress[]) list.toArray(all);
	    return all;
	  }

	  /**
	   * Add attachment
	   * @param attachment
	   */
		public void addAttachment(SMTPAttachment attachment) {
			this.attachments.add(attachment);
		}

		/**
		 * get attachmet
		 * @return
		 */
		public List<SMTPAttachment> getAttachments() {
			return attachments;
		}
		
		/**
		 * set attachment
		 * @param attachments
		 */
		public void setAttachments(List<SMTPAttachment> lattachments) {
			this.attachments.addAll(lattachments);
		}
		
}
