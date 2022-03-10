package com.example.springbootmailfiltering.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Configuration
public class ImapConnectionConfig {

    @Value("${mail.imap.host}")
    String host;

    @Value("${mail.imap.port}")
    String port;

    @Value("${mail.imap.username}")
    String username;

    @Value("${mail.imap.password}")
    String password;

     Properties properties;

    public Properties getProperties() {
        return setProperties();
    }

    public Properties setProperties() {
        Properties properties = new Properties();

        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));
        return properties;
    }

    public Store setSessionStoreProperties(Properties properties) throws NoSuchProviderException, MessagingException {
        Session session = Session.getDefaultInstance(properties);

        Store store = session.getStore("imap");
        store.connect(username, password);
        return store;
    }
}

