package com.artyom.esalr234.message;

import com.artyom.esalr234.message.utils.ChangeLogInfo;
import com.artyom.esalr234.model.ChangeLog;
import com.artyom.esalr234.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Component
public class MailConsumer {

    @Value("${spring.mail.username}")
    private String senderMail;

    private final NotificationRepository notificationRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MailConsumer.class);

    private final JavaMailSender javaMailSender;

    public void sendNotificationEmail(ChangeLog changeLog, String recipient, String sender) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo(recipient);
        emailMessage.setFrom(sender);
        emailMessage.setSubject("Delete Notification");
        emailMessage.setText(changeLog.toString());
        javaMailSender.send(emailMessage);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.mail}"})
    public void consumeMail(ChangeLogInfo changeLogInfo){
        for (String to: notificationRepository.findEmailsByNotification(changeLogInfo.getChangeLogInfo())){
            sendNotificationEmail(changeLogInfo.getChangeLog(), to, senderMail);
            LOGGER.info(String.format("Mail send to: %s", to));
        }
    }
}