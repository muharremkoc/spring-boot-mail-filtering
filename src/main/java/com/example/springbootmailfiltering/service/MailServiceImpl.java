package com.example.springbootmailfiltering.service;

import com.example.springbootmailfiltering.config.ImapConnectionConfig;
import com.example.springbootmailfiltering.model.Email;
import com.example.springbootmailfiltering.model.FileInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.util.*;


@RequiredArgsConstructor
@Slf4j
@Service
public class MailServiceImpl implements MailService{

    final ImapConnectionConfig imapConnectionConfig;

    @Override
    public List<Email> getMails(int count) throws MessagingException, IOException {
        List<Email> emails=new ArrayList<>();
        Properties properties = imapConnectionConfig.getProperties();
        Store store = imapConnectionConfig.setSessionStoreProperties(properties);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        Message[] arrayMessages =inbox.getMessages();
        int messageCount=count;
        //Message [] filterMessages=messageFilter(inbox,count,count);
        for (int i = 1; i < messageCount; i++) {
            Message message = arrayMessages[i];
            Address[] fromAddress = message.getFrom();
            var a=message.getFrom()[0].toString();

            String from = a;
            String subject = message.getSubject();
            Date sentDate = message.getSentDate();
            List<FileInfo> attachments = new ArrayList<>();

            if (message.getContentType().contains("multipart")) {
                attachments = getAttachments(message);
            }

            emails.add( Email.builder()
                    .from(from)
                    .subject(subject)
                    .sentDate(sentDate)
                    .attachment(attachments)
                    .build());
        }
        inbox.close(false);
        store.close();

        return emails;
    }

    @Override
    public List<Email> getMailsStartBetweenEnd(int start, int end) throws MessagingException, IOException {
        List<Email> emails=new ArrayList<>();
        Properties properties = imapConnectionConfig.getProperties();
        Store store = imapConnectionConfig.setSessionStoreProperties(properties);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        for (int i = start; i <= end; i++) {
            Message message = inbox.getMessage(i);
            Address[] fromAddress = message.getFrom();

            String from =message.getFrom()[0].toString();
            String subject = message.getSubject();

            Date sentDate = message.getSentDate();
            List<FileInfo> attachments = new ArrayList<>();
            if (message.getContentType().contains("multipart")) {
                attachments = getAttachments(message);
            }
            emails.add( Email.builder()
                    .messageCount("Message:"+i)
                    .from(from)
                    .subject(subject)
                    .sentDate(sentDate)
                    .attachment(attachments)
                    .build());
        }
        inbox.close(false);
        store.close();

        return emails;
    }

    private List<FileInfo> getAttachments(Message message) throws MessagingException, IOException {
        List<FileInfo> result = new ArrayList<FileInfo>();
        Multipart multiPart = (Multipart) message.getContent();
        int numberOfParts = multiPart.getCount();
        for (int partCount = 0; partCount < numberOfParts; partCount++) {
            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                byte[] data = part.getInputStream().readAllBytes();
                FileInfo attachment = FileInfo.builder()
                        .name(MimeUtility.decodeText(part.getFileName()))
                        .type(part.getContentType())
                        .size(Long.valueOf(data.length))
                        .data(data)
                        .build();
                result.add(attachment);

            }
        }
        return result;
    }

    private int messageCount(Folder folder) throws MessagingException {
        return folder.getMessageCount();
    }
}
