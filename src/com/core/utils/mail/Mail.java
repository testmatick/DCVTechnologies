package com.core.utils.mail;


import com.core.utils.Constants;
import com.core.reporting.Reporter;

import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {
    private static Folder openedFolder;
    private static Store store;

    private static void connect(String login, String password) throws MessagingException {
        Matcher matcher = Pattern.compile(".(\\+[0-9a-zA-Z]+)@").matcher(login);
        String trueLogin = matcher.find() ? login.replace(matcher.group(1), "") : login;

        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, null);
        store = session.getStore("imaps");
        store.connect("imap.gmail.com", trueLogin, password);
    }

    private static void disconnect() throws MessagingException {
        if (openedFolder != null) {
            openedFolder.close(true);
            openedFolder = null;
        }

        if (store != null) {
            store.close();
            store = null;
        }
    }


    private static List<Message> getUnreadMessages(String folderName) throws MessagingException {
        List<Message> unreadMessages = new ArrayList<Message>();
        if (store != null) {
            openedFolder = store.getFolder(folderName);
            openedFolder.open(Folder.READ_WRITE);
            Message messages[] = openedFolder.getMessages();

            for (Message msg : messages) {
                if (!msg.isSet(Flags.Flag.SEEN)) {
                    unreadMessages.add(msg);
                }
            }
        }

        return unreadMessages;
    }

    public static List<EmailMessage> getEmailMessages(String email, String password, String expectedSubject) {
        return getEmailMessages(email, password, "Inbox", expectedSubject);
    }

    public static List<EmailMessage> waitForEmail(String email, String password, String expectedSubject) {
        Reporter.log("Start waiting for email with subject which contains '" + expectedSubject + "' (up to " + Constants.ELEMENT_EXTRALONG_TIMEOUT_SECONDS + " seconds)..");
        long startTime = System.currentTimeMillis();
        List<EmailMessage> messages = getEmailMessages(email, password, expectedSubject);
        while (messages.size() == 0 && System.currentTimeMillis() - startTime < Constants.ELEMENT_EXTRALONG_TIMEOUT_SECONDS * 1000) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            messages = getEmailMessages(email, password, expectedSubject);
        }
        return messages;
    }

    public static List<EmailMessage> getEmailMessages(String login, String password, String folder, String expectedSubject) {
        List<EmailMessage> ret = new ArrayList<EmailMessage>();

        try {
            connect(login, password);

            List<Message> messages = getUnreadMessages(folder);

            for (Message msg : messages) {
                String from = "unknown";
                if (msg.getReplyTo().length >= 1) {
                    from = msg.getReplyTo()[0].toString();
                } else if (msg.getFrom().length >= 1) {
                    from = msg.getFrom()[0].toString();
                }

                String subject = msg.getSubject();
                Object content = msg.getContent();
                String body = "";
                if (content instanceof Multipart) {
                    int parts = ((Multipart) content).getCount();
                    for (int i = 0; i < parts; i++) {
                        body += ((Multipart) content).getBodyPart(i).getContent();
                    }
                } else {
                    body = (String) content;
                }

                EmailMessage email = new EmailMessage();
                email.setFrom(from);
                email.setBody(body);
                email.setSubject(subject);

                if (expectedSubject != null && !msg.getSubject().contains(expectedSubject)) {
                    msg.setFlag(Flags.Flag.SEEN, false);
                }
                if (expectedSubject != null && msg.getSubject().contains(expectedSubject)) {
                    ret.add(email);
                } else if (expectedSubject == null) {
                    ret.add(email);
                }
            }
        } catch (NoSuchProviderException nspe) {
            Reporter.log("<b>Email reading exception! Caught NoSuchProviderException.</b>");
        } catch (MessagingException mse) {
            Reporter.log("<b>Email reading exception! Caught MessagingException.</b>");
        } catch (IOException ioe) {
            Reporter.log("<b>Email reading exception! Caught MessagingException.</b>");
        } finally {

            try {
                disconnect();
            } catch (MessagingException mse) {
                Reporter.log("<b>Email reading exception! Caught MessagingException @ Closing connection.</b>");
            }
        }
        return ret;
    }

    public static void clearInboxFolder(String login, String password) {
        Reporter.log("Clearing inbox folder..");
        clearFolder(login, password, "Inbox");
    }

    public static void clearFolder(String login, String password, String folderName) {
        try {
            connect(login, password);

            openedFolder = store.getFolder(folderName);
            openedFolder.open(Folder.READ_WRITE);
            Message messages[] = openedFolder.getMessages();

            for (Message msg : messages)
                msg.setFlag(Flags.Flag.DELETED, true);

        } catch (NoSuchProviderException nspe) {
            Reporter.log("<b>Email reading exception! Caught NoSuchProviderException.</b>");
        } catch (MessagingException mse) {
            Reporter.log("<b>Email reading exception! Caught MessagingException.</b>");

        } finally {

            try {
                disconnect();
            } catch (MessagingException mse) {
                Reporter.log("<b>Email reading exception! Caught MessagingException @ Closing connection.</b>");
            }
        }

    }

}
