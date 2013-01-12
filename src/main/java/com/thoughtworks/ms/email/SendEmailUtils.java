package com.thoughtworks.ms.email;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmailUtils {
    private static Logger log = Logger.getLogger(SendEmailUtils.class.getName());

    public static boolean sendEmailUser(PropertiesEmail props, String message, Address address) {
        return SendEmailUtils.sendEmail(props, message, Collections.EMPTY_LIST, address, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    public static boolean sendEmailUser(PropertiesEmail props, String message, Address address, List<Address> ccList, List<Address> bccList) {
        return SendEmailUtils.sendEmail(props, message, Collections.EMPTY_LIST, address, ccList, bccList);
    }

    private static boolean sendEmailWithAttachments(PropertiesEmail props, String message, List<String> attachments, Address address, List<Address> ccList, List<Address> bccList) {
        return SendEmailUtils.sendEmail(props, message, attachments, address, ccList, bccList);
    }

    private static boolean sendEmail(PropertiesEmail props, String mensajeHTML, List<String> attachments, Address address, List<Address> ccList, List<Address> bccList) {
        SMTPSession smtp = new SMTPSession(props);

        SMTPMail mail = new SMTPMail(props.getMAIL_ISSUE_VALUE(), props.getFromAddress());

        mail.addToAddress(address);
        mail.addCcAddress(ccList.toArray(new Address[0]));
        mail.addBccAddress(bccList.toArray(new Address[0]));
        mail.addAttachment(attachments.toArray(new String[0]));

        try {
            log.info("Send email " + address.getUserAddress() + " with issue " + props.getMAIL_ISSUE_VALUE());
            smtp.sendMail(props, mail, mensajeHTML);

            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed send email: " + e.getMessage(), e);
            return false;
        }
    }
}
