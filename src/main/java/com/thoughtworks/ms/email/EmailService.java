package com.thoughtworks.ms.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public boolean sendEmailUser(PropertiesEmail props, String message, Address address) {
        return sendEmail(props, message, Collections.EMPTY_LIST, address, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    public boolean sendEmailUser(PropertiesEmail props, String message, Address address, List<Address> ccList, List<Address> bccList) {
        return sendEmail(props, message, Collections.EMPTY_LIST, address, ccList, bccList);
    }

    private boolean sendEmailWithAttachments(PropertiesEmail props, String message, List<String> attachments, Address address, List<Address> ccList, List<Address> bccList) {
        return sendEmail(props, message, attachments, address, ccList, bccList);
    }

    private boolean sendEmail(PropertiesEmail props, String message, List<String> attachments, Address address, List<Address> ccList, List<Address> bccList) {

        SMTPMail mail = new SMTPMail(props.getMAIL_ISSUE_VALUE(), props.getFromAddress());

        mail.addToAddress(address);
        mail.addCcAddress(ccList.toArray(new Address[0]));
        mail.addBccAddress(bccList.toArray(new Address[0]));
        mail.addAttachment(attachments.toArray(new String[0]));

        try {
            LOGGER.info("Send email " + address.getUserAddress() + " with issue " + props.getMAIL_ISSUE_VALUE());
            new SMTPSession(props).sendMail(props, mail, message);

            return true;
        } catch (Exception e) {
            LOGGER.warn("Failed send email: " + e.getMessage(), e);
            return false;
        }
    }
}
