package com.thoughtworks.ms.email;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SendEmailUtilsTest {

    @Test
    public void should_send_email_with_163_as_smtp_server() {
        PropertiesEmail props = new PropertiesEmail();
        props.setMAIL_SERVER_VALUE("smtp.163.com");
        props.setMAIL_ADDRESS_SENDER_VALUE("vinci_zhang@163.com");

        props.setMAIL_USER_VALUE("vinci_zhang");
        props.setMAIL_PASSWORD_VALUE("j1ngn0ng");
        props.setMAIL_AUTENTICATION_VALUE(true);

        props.setMAIL_ISSUE_VALUE("a test email");

        boolean result = SendEmailUtils.sendEmailUser(props, "Just a test", "Xiaoqing Zhang", "vinci.zhang@gmail.com");
        assertThat(result, is(true));
    }
}
