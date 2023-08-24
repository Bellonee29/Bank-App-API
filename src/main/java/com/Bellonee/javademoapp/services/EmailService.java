package com.Bellonee.javademoapp.services;

import com.Bellonee.javademoapp.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlart(EmailDetails emailDetails);
    void sendEmailWithAttachment(EmailDetails emailDetails);
}
