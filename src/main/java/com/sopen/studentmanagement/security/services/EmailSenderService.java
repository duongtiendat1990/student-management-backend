package com.sopen.studentmanagement.security.services;

import com.sopen.studentmanagement.model.ConfirmationToken;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {
  @Autowired
  private ConfirmationTokenRepository confirmationTokenRepository;
  private JavaMailSender javaMailSender;
  @Value("${spring.mail.username}")
  private String mailSenderName;
  @Autowired
  public EmailSenderService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Async
  public void sendEmail(SimpleMailMessage email) {
    javaMailSender.send(email);
  }

  public void sendEmailCreateUser(User user) {
    ConfirmationToken confirmationToken = new ConfirmationToken(user);

    confirmationTokenRepository.save(confirmationToken);

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(user.getEmail());
    mailMessage.setSubject("Complete Registration!");
    mailMessage.setFrom(mailSenderName);
    mailMessage.setText("To confirm your account, please click here : "
      + "http://localhost:8080/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());

    sendEmail(mailMessage);
  }

  public void sendEmailForgotPassword(User user) {
    ConfirmationToken confirmationToken = new ConfirmationToken(user);

    confirmationTokenRepository.save(confirmationToken);

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(user.getEmail());
    mailMessage.setSubject("Complete Password Reset!");
    mailMessage.setFrom(mailSenderName);
    mailMessage.setText("To complete the password reset process, please click here : "
      + "http://localhost:8080/api/auth/confirm-reset?token=" + confirmationToken.getConfirmationToken());

    sendEmail(mailMessage);
  }
}