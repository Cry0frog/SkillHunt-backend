package com.skillhunt.db.email;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class EmailService {

  private final JavaMailSender emailSender;

  public void sendSupportMessage(String fio, String email, String msg) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("info@museup.ru");
    message.setFrom("info@museup.ru");
    message.setSubject("Новый вопрос");
    message.setText(buildMessageForSupport(fio, msg, email));
    emailSender.send(message);
  }

  private String buildMessageForSupport(String fio, String message, String email) {
    String msg = String.format("Новый вопрос от пользователя %s. %s. Ответить по почте %s.", fio, message, email);
    return msg;
  }

}