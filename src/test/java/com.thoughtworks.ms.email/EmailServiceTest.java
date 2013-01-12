package com.thoughtworks.ms.email;

import org.junit.Before;
import org.junit.Test;

import static com.thoughtworks.ms.email.Address.anAddress;
import static com.thoughtworks.ms.email.Email.anEmail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmailServiceTest {

    private EmailService emailService;

    @Before
    public void before() {
        emailService = new EmailService();
    }

    @Test
    public void should_send_email_with_163_as_smtp_server() {
        PropertiesEmail props = new PropertiesEmail();
        props.setMailServerHost("smtp.163.com");

        props.setAuthenticationUserName("vinci_zhang");
        props.setAuthenticationPassword("j1ngn0ng");
        props.setAuthenticationNeeded(true);

        boolean result = emailService.sendEmail(props, anEmail(anAddress("Admin", "vinci_zhang@163.com"), "a test email", "test body", anAddress("Xiaoqing Zhang", "vinci.zhang@gmail.com")));
        assertThat(result, is(true));
    }
}
