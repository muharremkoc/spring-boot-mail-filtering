package com.example.springbootmailfiltering.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Email {

    String messageCount;

    String from;

    Date sentDate;

    String subject;

   List<FileInfo> attachment;


}
