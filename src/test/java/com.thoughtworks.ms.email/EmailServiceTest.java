package com.thoughtworks.ms.email;

import org.junit.Test;

import static com.thoughtworks.ms.email.model.Address.anAddress;
import static com.thoughtworks.ms.email.model.Email.anEmail;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmailServiceTest {

    private EmailService emailService;

    @Test
    public void should_send_email_with_163_as_smtp_server() {
        EmailConfiguration props = new EmailConfiguration();
        props.setMailServerHost("smtp.163.com");

        props.setAuthenticationUserName("vinci_zhang");
        props.setAuthenticationPassword("j1ngn0ng");
        props.setAuthenticationNeeded(true);
        emailService = new EmailService(props);

        boolean result = emailService.sendEmail(anEmail(anAddress("Admin", "vinci_zhang@163.com"), "a test email", "test body", anAddress("Xiaoqing Zhang", "vinci.zhang@gmail.com")));
        assertThat(result, is(true));
    }
}
