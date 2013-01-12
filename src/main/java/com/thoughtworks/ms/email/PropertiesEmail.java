package com.thoughtworks.ms.email;

public class PropertiesEmail {

    public final int MAIL_PORT_DEFAULT_VALUE = 25;

    public int smtpPort = MAIL_PORT_DEFAULT_VALUE;

    public String mailServerHost = null;

    /**
     * If authentication is enabled (true), you must enter values ​​for properties:
     * authenticationPassword, authenticationUserName
     */
    public boolean authenticationNeeded = false;

    public String authenticationUserName = null;

    public String authenticationPassword = null;

    public String fromAddress = null;

    public String fromUserName = null;

    /**
     * Issues email
     */
    public String MAIL_ISSUE_VALUE = null;


    //Others properties
    public final String MAIL_SERVER = "mail.server";

    public final String MAIL_FROM = "mail.from";

    public final String MAIL_PORT = "mail.port";

    public final String MAIL_USER = "mail.user";

    public final String MAIL_PASSWORD = "mail.password";

    public final String MAIL_AUTENTICATION = "mail.autentication";

    public final String MAIL_ISSUE = "mail.issue";

    public final String MAIL_CC = "mail.cc";

    public final String MAIL_BCC = "mail.bcc";

    public final String MAIL_ACTIVATE_BCC = "mail.activatebcc";

    public final String MAIL_ADDRESS_SENDER = "mail.address.sender";

    public final String MAIL_NAME_SENDER = "mail.name.sender";

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