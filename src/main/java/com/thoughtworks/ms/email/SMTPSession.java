package com.thoughtworks.ms.email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.MimeMessage;

public class SMTPSession implements TransportListener {

    private static final Logger LOGGER = Logger.getLogger(SMTPSession.class.getName());

    /**
     * Smtp auth
     */
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

    /**
     * Smtp host
     */
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";

    /**
     * Cte True
     */
    private static final Boolean TRUE = true;

    /**
     * Cte False
     */
    private static final Boolean FALSE = false;

    /**
     * Cte True
     */
    private static final String TRUE_STRING = "true";

    /**
     * Cte False
     */
    private static final String FALSE_STRING = "false";

    /**
     * Properties
     */
    private PropertiesEmail props = null;

    /**
     * Properties
     */
    private Properties properties = null;

    /**
     * Format ISO
     */
    private static final String TYPE_HTML_ISO_88591 = "text/html; charset=ISO-8859-1";

    /**
     * SMTP
     */
    private static final String SMTP = "smtp";

    public SMTPSession(PropertiesEmail props) {
        this.props = props;
        this.properties = new Properties();
    }

    public void sendMail(final PropertiesEmail props, SMTPMail mail, String emailContent) throws Exception {
        properties.put(MAIL_SMTP_HOST, props.getMailServerHost());

        //Required Autentication
        if (props.isAuthenticationRequired()) {
            properties.put(MAIL_SMTP_AUTH, TRUE_STRING);
        }

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        props.getAuthenticationUserName(),
                        props.getAuthenticationPassword());
            }
        });
        session.setDebug(true);

        MimeMessage message = mail.buildMessage(emailContent, session);

        sendEmail(session, message, this.props.getMailServerHost(), this.props.getMailServerPort(), this.props.getAuthenticationUserName(), this.props.getAuthenticationPassword());
    }

    private void sendEmail(Session session, MimeMessage message, String host, int port, String user, String password) throws MessagingException {
        Transport transport = session.getTransport(SMTP);
        transport.addTransportListener(this);
        transport.connect(host, port, user, password);

        transport.send(message);
        transport.close();
    }

    @Override
    public void messageDelivered(TransportEvent e) {
        LOGGER.log(Level.INFO, "Message was delivered successfully.");
    }

    @Override
    public void messageNotDelivered(TransportEvent e) {
        LOGGER.log(Level.WARNING, "Message was not delivered: " + e.getType());
    }

    @Override
    public void messagePartiallyDelivered(TransportEvent e) {
        LOGGER.log(Level.WARNING, "Message was partially delivered: " + e.getType());
    }
}