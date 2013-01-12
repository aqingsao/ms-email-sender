package com.thoughtworks.ms.email;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

public class Address {
    private String userName;
    private String userAddress;

    public Address(String userName, String userAddress) {
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public InternetAddress toInternetAddress() {
        try {
            return new InternetAddress(this.userAddress, this.userName);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
