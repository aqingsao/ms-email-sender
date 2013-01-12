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

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

    private static final String MAIL_SMTP_HOST = "mail.smtp.host";

    private static final String SMTP = "smtp";

    private PropertiesEmail props = null;

    public SMTPSession(PropertiesEmail props) {
        this.props = props;
    }

    public void sendMail(final PropertiesEmail props, SMTPMail mail, String emailContent) throws Exception {
        Properties properties = new Properties();
        properties.put(MAIL_SMTP_HOST, props.getMailServerHost());

        if (props.isAuthenticationRequired()) {
            properties.put(MAIL_SMTP_AUTH, "true");
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