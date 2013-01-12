package com.thoughtworks.ms.email;

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
}
