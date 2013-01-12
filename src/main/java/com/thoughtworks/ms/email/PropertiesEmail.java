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

    public void setAuthenticationNeeded(Boolean mAIL_AUTENTICATION_VALUE) {
        authenticationNeeded = mAIL_AUTENTICATION_VALUE;
    }

    boolean isAuthenticationRequired() {
        return authenticationNeeded;
    }

    int getMailServerPort() {
        return smtpPort;
    }
}