package com.example.springbootmailfiltering.controller;

import com.example.springbootmailfiltering.model.Email;
import com.example.springbootmailfiltering.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    @GetMapping("/allMails")
    public List<Email> getMails(@RequestParam int count) throws MessagingException, IOException {
        return mailService.getMails(count);
    }

    @GetMapping("/allMails/startBetweenEnd")
    public List<Email> getMailsStartBetweenEnd(@RequestParam int start,@RequestParam int end) throws MessagingException, IOException {
        return mailService.getMailsStartBetweenEnd(start, end);
    }
}
