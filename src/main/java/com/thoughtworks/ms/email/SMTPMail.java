package com.thoughtworks.ms.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SMTPMail {

    /**
     * log
     */
    private Logger log = Logger.getLogger("SMTPMail");

    /**
     * toAddresses
     */
    protected List<Address> toAddresses = new ArrayList<Address>();

    /**
     * ccAddresses
     */
    protected List<Address> ccAddresses = new ArrayList<Address>();

    /**
     * bccAddresses
     */
    protected List<Address> bccAddresses = new ArrayList<Address>();

    /**
     * replyToAddresses
     */
    protected List<Address> replyToAddresses = new ArrayList<Address>();

    /**
     * fromName
     */
    protected String fromName = null;

    /**
     * fromAddress
     */
    protected String fromAddress = null;

    /**
     * subject
     */
    protected String subject = null;

    private List<SMTPAttachment> attachments = new ArrayList<SMTPAttachment>();

    private static final String TYPE_HTML_UTF_8 = "text/html; charset=UTF-8";

    public SMTPMail(String fromName, String fromAddress, String subject) {
        this.fromName = fromName;
        this.fromAddress = fromAddress;
        this.subject = subject;
    }

    public void addToAddress(String name, String address) {
        toAddresses.add(new Address(name, address));
    }

    public void addCcAddress(String name, String address) {
        ccAddresses.add(new Address(name, address));
    }

    public void addBccAddress(String name, String address) {
        bccAddresses.add(new Address(name, address));
    }

    public void addReplyToAddress(String name, String address) {
        replyToAddresses.add(new Address(name, address));
    }

    private InternetAddress[] getAddresses(List<Address> addresses) throws UnsupportedEncodingException {
        InternetAddress[] internetAddresses = new InternetAddress[addresses.size()];
        int count = 0;
        for (Address address : addresses) {
            internetAddresses[count++] = new InternetAddress(address.getUserAddress(), address.getUserName());
        }

        return internetAddresses;
    }

    public void addAttachment(SMTPAttachment attachment) {
        this.attachments.add(attachment);
    }

    public List<SMTPAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<SMTPAttachment> lattachments) {
        this.attachments.addAll(lattachments);
    }

    MimeMessage prepareMessage(Session session) throws Exception {
        //Create message
        MimeMessage message = new MimeMessage(session);

        // Properties of message
        message.setFrom(new InternetAddress(fromAddress, fromName));
        message.setSubject(subject);
        message.setSentDate(new java.util.Date());

        message.setReplyTo(getAddresses(replyToAddresses));
        message.setRecipients(Message.RecipientType.TO, getAddresses(toAddresses));
        message.setRecipients(Message.RecipientType.CC, getAddresses(ccAddresses));
        message.setRecipients(Message.RecipientType.BCC, getAddresses(bccAddresses));

        return message;
    }

    MimeMessage buildMessage(String emailContent, Session session) throws Exception {
        MimeMultipart multiPart = new MimeMultipart();

        MimeBodyPart bodyPart = new MimeBodyPart();
        //Content
        bodyPart.setContent(emailContent, TYPE_HTML_UTF_8);
        multiPart.addBodyPart(bodyPart);

        // Attachment files
        for (SMTPAttachment attachment : getAttachments()) {
            multiPart.addBodyPart(attachment.asBodyPart());
        }

        MimeMessage message = prepareMessage(session);
        message.setContent(multiPart);

        message.saveChanges();
        return message;
    }
}
