package kg.angryelizar.todoapi.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kg.angryelizar.todoapi.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    @Override
    public void sendEmail(String toEmail, String taskName, String taskDescription, String taskStatus, LocalDateTime creationDate, String nameOfUser) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        mimeMessageHelper.setFrom(EMAIL_FROM, "To Do API Support");
        mimeMessageHelper.setTo(toEmail);
        String subject = "Новая задача создана";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = creationDate.format(formatter);

        String content = "<html><body>";
        content += "<h2 style='color: #007bff;'>Поздравляем!</h2>";
        content += "<p>Здравствуйте, " + nameOfUser + "</p>";
        content += "<p>Вы создали новую задачу с названием: <strong>" + taskName + "</strong>.</p>";
        content += "<p><strong>Описание:</strong> " + taskDescription + "</p>";
        content += "<p><strong>Статус:</strong> " + taskStatus + "</p>";
        content += "<p><strong>Дата создания:</strong> " + formattedDate + "</p>";
        content += "<p>Удачи в исполнении задачи!</p>";
        content += "<p>Если у вас возникли вопросы, пожалуйста, не стесняйтесь обращаться в поддержку.</p>";
        content += "<p>С уважением,</p>";
        content += "<p>Команда To Do API</p>";
        content += "</body></html>";

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }



}
