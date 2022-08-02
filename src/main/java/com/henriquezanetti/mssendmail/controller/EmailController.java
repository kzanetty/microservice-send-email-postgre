package com.henriquezanetti.mssendmail.controller;

import com.henriquezanetti.mssendmail.dto.EmailDto;
import com.henriquezanetti.mssendmail.model.EmailModel;
import com.henriquezanetti.mssendmail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendMail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
