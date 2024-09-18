package kg.angryelizar.todoapi.service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
public interface EmailService {
    void sendEmail(String toEmail, String taskName, String taskDescription, String taskStatus, LocalDateTime creationDate, String nameOfUser) throws MessagingException, UnsupportedEncodingException;
}
