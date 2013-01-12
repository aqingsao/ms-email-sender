package com.thoughtworks.ms.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SMTPMail {

    private Logger LOGGER = Logger.getLogger(SMTPMail.class.getName());

    protected List<Address> toAddresses = new ArrayList<Address>();

    protected List<Address> ccAddresses = new ArrayList<Address>();

    protected List<Address> bccAddresses = new ArrayList<Address>();

    protected List<Address> replyToAddresses = new ArrayList<Address>();

    private Address from;

    protected String subject = null;

    private List<String> attachments = new ArrayList<String>();
    private static final String TYPE_HTML_UTF_8 = "text/html; charset=UTF-8";

    public SMTPMail(String subject, Address from) {
        this.from = from;

        this.subject = subject;
    }

    public void addToAddress(Address... addresses) {
        for (Address address : addresses) {
            toAddresses.add(address);
        }
    }

    public void addCcAddress(Address... addresses) {
        for (Address address : addresses) {
            ccAddresses.add(address);
        }
    }

    public void addBccAddress(Address... addresses) {
        for (Address address : addresses) {
            bccAddresses.add(address);
        }
    }

    public void addReplyToAddress(Address... addresses) {
        for (Address address : addresses) {
            replyToAddresses.add(address);
        }
    }

    private InternetAddress[] getAddresses(List<Address> addresses) throws UnsupportedEncodingException {
        InternetAddress[] internetAddresses = new InternetAddress[addresses.size()];
        int count = 0;
        for (Address address : addresses) {
            internetAddresses[count++] = address.toInternetAddress();
        }

        return internetAddresses;
    }

    public void addAttachment(String... attachments) {
        for (String attachment : attachments) {
            this.attachments.add(attachment);
        }
    }

    MimeMessage buildMessage(String emailContent, Session session) throws Exception {
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

        message.setFrom(from.toInternetAddress());
        message.setSubject(subject);
        message.setSentDate(new java.util.Date());

        message.setReplyTo(getAddresses(replyToAddresses));
        message.setRecipients(Message.RecipientType.TO, getAddresses(toAddresses));
        message.setRecipients(Message.RecipientType.CC, getAddresses(ccAddresses));
        message.setRecipients(Message.RecipientType.BCC, getAddresses(bccAddresses));

        return message;
    }
}
