package com.example.springbootmailfiltering.service;

import com.example.springbootmailfiltering.model.Email;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface MailService {

    List<Email> getMails(int count) throws MessagingException, IOException;

    List<Email> getMailsStartBetweenEnd(int start,int end) throws MessagingException, IOException;
}
