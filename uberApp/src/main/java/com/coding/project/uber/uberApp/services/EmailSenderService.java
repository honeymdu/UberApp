package com.coding.project.uber.uberApp.services;

import com.coding.project.uber.uberApp.dto.SmtpEmailDetailsDto;

public interface EmailSenderService {

    public String sendSimpleMail(SmtpEmailDetailsDto SmtpEmailDetails);

    public String sendMailWithAttachment(SmtpEmailDetailsDto SmtpEmailDetails);
}
