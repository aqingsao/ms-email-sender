package com.thoughtworks.ms.email;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmailServiceTest {

    @Test
    public void should_send_email_with_163_as_smtp_server() {
        PropertiesEmail props = new PropertiesEmail();
        props.setMailServerHost("smtp.163.com");
        props.setFromAddress("vinci_zhang@163.com");

        props.setAuthenticationUserName("vinci_zhang");
        props.setAuthenticationPassword("j1ngn0ng");
        props.setAuthenticationNeeded(true);

        props.setMAIL_ISSUE_VALUE("a test email");

        boolean result = new EmailService().sendEmailUser(props, "Just a test", new Address("Xiaoqing Zhang", "vinci.zhang@gmail.com"));
        assertThat(result, is(true));
    }
}
