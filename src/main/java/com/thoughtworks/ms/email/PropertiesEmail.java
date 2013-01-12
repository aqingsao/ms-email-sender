package com.thoughtworks.ms.email;

public class PropertiesEmail {

    public final int MAIL_PORT_DEFAULT_VALUE = 25;

    public int smtpPort = MAIL_PORT_DEFAULT_VALUE;

    /**
     * Server email
     * Name for email server
     */
    public String mailServerHost = null;

    /**
     * Autentication server email
     * If authentication is enabled (true),
     * you must enter values ​​for properties:
     * authenticationPassword
     * authenticationUserName
     */
    public boolean authenticationNeeded = false;


    /**
     * Name account email
     * User for authentication on the mail server
     * If the property authenticationNeeded is enabled (true),
     * you must enter the field authenticationUserName and authenticationPassword
     */
    public String authenticationUserName = null;


    /**
     * Password server email
     * Password for authentication on the mail server
     * If the property authenticationNeeded is enabled (true),
     * you must enter the field authenticationUserName and authenticationPassword
     */
    public String authenticationPassword = null;


    /**
     * Email address sender
     */
    public String fromAddress = null;


    /**
     * Name email sender
     */
    public String fromUserName = null;


    /**
     * Issues email
     */
    public String MAIL_ISSUE_VALUE = null;

    /**
     * Copy email
     */
    public String MAIL_CC_VALUE = null;

    /**
     * Copy email. Name
     */
    public String MAIL_CC_NAME_VALUE = null;

    /**
     * Copy email. Address email
     */
    public String MAIL_CC_ADDRESS_VALUE = null;


    /**
     * Copy hide bcc
     * Must be enabled (true)  MAIL_ACTIVATE_BCC_VALUE property
     */
    public String MAIL_BCC_VALUE = null;


    /**
     * Copy hide bcc. Name bcc
     * Must be enabled (true)  MAIL_ACTIVATE_BCC_VALUE property
     */
    public String MAIL_BCC_NAME_VALUE = null;


    /**
     * Copy hide bcc. Address email bcc
     * Must be enabled (true)  MAIL_ACTIVATE_BCC_VALUE property
     */
    public String MAIL_BCC_ADDRESS_VALUE = null;


    /**
     * Activate bcc
     * Alpha To activate the email sending blind copy, must be activated (true)
     * field MAIL_ACTIVATE_BCC_VALUE
     * The blind copy was sent to the email address provided on the property:
     * MAIL_BCC_VALUE
     * MAIL_BCC_NAME_VALUE
     * MAIL_BCC_ADDRESS_VALUE
     */
    public Boolean MAIL_ACTIVATE_BCC_VALUE = new Boolean(true);


    //Others properties
    /**
     * Key MAIL_SERVER
     */
    public final String MAIL_SERVER = "mail.server";

    /**
     * Key MAIL_FROM
     */
    public final String MAIL_FROM = "mail.from";

    /**
     * Key MAIL_PORT
     */
    public final String MAIL_PORT = "mail.port";

    /**
     * Key MAIL_USER
     */
    public final String MAIL_USER = "mail.user";

    /**
     * Key MAIL_PASSWORD
     */
    public final String MAIL_PASSWORD = "mail.password";

    /**
     * Key MAIL_AUTENTICATION
     */
    public final String MAIL_AUTENTICATION = "mail.autentication";

    /**
     * Key MAIL_ISSUE
     */
    public final String MAIL_ISSUE = "mail.issue";

    /**
     * Key MAIL_CC
     */
    public final String MAIL_CC = "mail.cc";

    /**
     * Key MAIL_BCC
     */
    public final String MAIL_BCC = "mail.bcc";

    /**
     * Key MAIL_ACTIVATE_BCC
     */
    public final String MAIL_ACTIVATE_BCC = "mail.activatebcc";

    /**
     * Key MAIL_USER_SENDER
     */
    public final String MAIL_ADDRESS_SENDER = "mail.address.sender";

    /**
     * Key MAIL_NAME_SENDER
     */
    public final String MAIL_NAME_SENDER = "mail.name.sender";

    /**
     * Constructor
     */
    public PropertiesEmail() {

    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getAuthenticationUserName() {
        return authenticationUserName;
    }

    public void setAuthenticationUserName(String mAIL_USER_VALUE) {
        authenticationUserName = mAIL_USER_VALUE;
    }

    public String getAuthenticationPassword() {
        return authenticationPassword;
    }

    public void setAuthenticationPassword(String mAIL_PASSWORD_VALUE) {
        authenticationPassword = mAIL_PASSWORD_VALUE;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMAIL_ISSUE_VALUE() {
        return MAIL_ISSUE_VALUE;
    }

    public void setMAIL_ISSUE_VALUE(String mAIL_ISSUE_VALUE) {
        MAIL_ISSUE_VALUE = mAIL_ISSUE_VALUE;
    }

    public void setAuthenticationNeeded(Boolean mAIL_AUTENTICATION_VALUE) {
        authenticationNeeded = mAIL_AUTENTICATION_VALUE;
    }

    public String getMAIL_CC_VALUE() {
        return MAIL_CC_VALUE;
    }

    public void setMAIL_CC_VALUE(String mAIL_CC_VALUE) {
        MAIL_CC_VALUE = mAIL_CC_VALUE;
    }

    public String getMAIL_BCC_VALUE() {
        return MAIL_BCC_VALUE;
    }

    public void setMAIL_BCC_VALUE(String mAIL_BCC_VALUE) {
        MAIL_BCC_VALUE = mAIL_BCC_VALUE;
    }

    public Boolean getMAIL_ACTIVATE_BCC_VALUE() {
        return MAIL_ACTIVATE_BCC_VALUE;
    }

    public void setMAIL_ACTIVATE_BCC_VALUE(Boolean mAIL_ACTIVATE_BCC_VALUE) {
        MAIL_ACTIVATE_BCC_VALUE = mAIL_ACTIVATE_BCC_VALUE;
    }

    public String getMAIL_SERVER() {
        return MAIL_SERVER;
    }

    public String getMAIL_FROM() {
        return MAIL_FROM;
    }

    public String getMAIL_PORT() {
        return MAIL_PORT;
    }

    public String getMAIL_USER() {
        return MAIL_USER;
    }

    public String getMAIL_PASSWORD() {
        return MAIL_PASSWORD;
    }

    public String getMAIL_AUTENTICATION() {
        return MAIL_AUTENTICATION;
    }

    public String getMAIL_ISSUE() {
        return MAIL_ISSUE;
    }

    public String getMAIL_ADDRESS_SENDER() {
        return MAIL_ADDRESS_SENDER;
    }

    public String getMAIL_NAME_SENDER() {
        return MAIL_NAME_SENDER;
    }

    boolean isAuthenticationRequired() {
        return authenticationNeeded;
    }

    int getMailServerPort() {
        return smtpPort;
    }

    Address getFromAddress() {
        return new Address(fromUserName, fromAddress);
    }
}