package com.thoughtworks.ms.email.model;

import com.thoughtworks.ms.email.EmailService;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Email {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private Address sender;
    protected Address replyToAddresses;
    protected String subject = null;
    private String message;
    private List<String> attachments = new ArrayList<String>();
    private Recipients recipients;

    private static final String TYPE_HTML_UTF_8 = "text/html; charset=UTF-8";

    private Email(Address sender, Address replyToAddresses, String subject, String message, List<String> attachments, Recipients recipients) {
        this.sender = sender;
        this.replyToAddresses = replyToAddresses;
        this.subject = subject;
        this.recipients = recipients;
        this.message = message;
        this.attachments = attachments;
    }

    public static Email anEmail(Address sender, String subject, String message, Address recipient) {
        return new Email(sender, null, subject, message, Collections.EMPTY_LIST, Recipients.oneRecipients(recipient));
    }

    public static Email anEmail(Address sender, String subject, String message, Recipients recipients) {
        return new Email(sender, null, subject, message, Collections.EMPTY_LIST, recipients);
    }

    public static Email anEmail(Address sender, Address replyToAddresses, String subject, String message, Address recipient) {
        return new Email(sender, replyToAddresses, subject, message, Collections.EMPTY_LIST, Recipients.oneRecipients(recipient));
    }

    public static Email anEmail(Address sender, Address replyToAddresses, String subject, String message, Recipients recipients) {
        return new Email(sender, replyToAddresses, subject, message, Collections.EMPTY_LIST, recipients);
    }

    public static Email anEmail(Address sender, String subject, String message, String attachment, Address recipient) {
        return new Email(sender, null, subject, message, Arrays.asList(attachment), Recipients.oneRecipients(recipient));
    }

    public static Email anEmail(Address sender, String subject, String message, String attachment, Recipients recipients) {
        return new Email(sender, null, subject, message, Arrays.asList(attachment), recipients);
    }

    public static Email anEmail(Address sender, Address replyToAddresses, String subject, String message, String attachment, Address recipient) {
        return new Email(sender, replyToAddresses, subject, message, Arrays.asList(attachment), Recipients.oneRecipients(recipient));
    }

    public static Email anEmail(Address sender, Address replyToAddresses, String subject, String message, String attachment, Recipients recipients) {
        return new Email(sender, replyToAddresses, subject, message, Arrays.asList(attachment), recipients);
    }

    public static Email anEmail(Address sender, String subject, String message, List<String> attachments, Address recipient) {
        return new Email(sender, null, subject, message, attachments, Recipients.oneRecipients(recipient));
    }

    public static Email anEmail(Address sender, String subject, String message, List<String> attachments, Recipients recipients) {
        return new Email(sender, null, subject, message, attachments, recipients);
    }

    public static Email anEmail(Address sender, Address replyToAddresses, String subject, String message, List<String> attachments, Address recipient) {
        return new Email(sender, replyToAddresses, subject, message, attachments, Recipients.oneRecipients(recipient));
    }

    public static Email anEmail(Address sender, Address replyToAddresses, String subject, String message, List<String> attachments, Recipients recipients) {
        return new Email(sender, replyToAddresses, subject, message, attachments, recipients);
    }

    public MimeMessage buildMessage(String emailContent, Session session) throws Exception {
        MimeMessage message = initMessage(session);

        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(asContentPart(emailContent));

        for (String attachment : attachments) {
            multiPart.addBodyPart(asAttachmentPart(attachment));
        }
        message.setContent(multiPart);

        message.saveChanges();
        return message;
    }

    private MimeBodyPart asAttachmentPart(String attachment) throws MessagingException {
        MimeBodyPart attachmentPart = new MimeBodyPart();

        FileDataSource dataSource = new FileDataSource(attachment);
        attachmentPart.setDataHandler(new DataHandler(dataSource));
        attachmentPart.setFileName(dataSource.getName());
        return attachmentPart;
    }

    private MimeBodyPart asContentPart(String emailContent) throws MessagingException {
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailContent, TYPE_HTML_UTF_8);
        return bodyPart;
    }

    private MimeMessage initMessage(Session session) throws Exception {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(sender.toInternetAddress());
        message.setSubject(subject);
        message.setSentDate(new java.util.Date());

        if (replyToAddresses != null) {
            message.setReplyTo(new javax.mail.Address[]{replyToAddresses.toInternetAddress()});
        }
        message.setRecipients(Message.RecipientType.TO, recipients.getToAddresses());
        message.setRecipients(Message.RecipientType.CC, recipients.getCCAddresses());
        message.setRecipients(Message.RecipientType.BCC, recipients.getBCCAddresses());

        return message;
    }

    public String getMessage() {
        return message;
    }

    public Address getToAddress() {
        return recipients.getToAddress();
    }

    public String getSubject() {
        return subject;
    }

    public List<Address> getToAddresses() {
        return recipients.getToList();
    }
}
