package com.henriquezanetti.mssendmail.service;

import com.henriquezanetti.mssendmail.enums.StatusEmail;
import com.henriquezanetti.mssendmail.model.EmailModel;
import com.henriquezanetti.mssendmail.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public EmailModel sendMail(EmailModel emailModel) {
        emailModel.setEmailDataSend(LocalDateTime.now());
        try {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setSubject(emailModel.getSubject());
            smm.setFrom(emailModel.getEmailFrom());
            smm.setTo(emailModel.getEmailTo());
            smm.setText(emailModel.getText());
            javaMailSender.send(smm);

            emailModel.setStatus(StatusEmail.SENT);
        } catch(MailException e) {
            emailModel.setStatus(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

}
