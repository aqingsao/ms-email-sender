package com.thoughtworks.ms.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public boolean sendEmail(PropertiesEmail props, Email mail) {
        try {
            LOGGER.info("Send email to " + mail.getToAddress().getUserAddress() + " with subject " + mail.getSubject());
            new SMTPSession(props).sendMail(props, mail);

            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed send email: " + e.getMessage(), e);
            return false;
        }
    }

}
