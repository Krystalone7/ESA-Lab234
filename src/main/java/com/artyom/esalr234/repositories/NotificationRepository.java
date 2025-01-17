package com.artyom.esalr234.repositories;

import com.artyom.esalr234.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query(value = "select notification.notification_email from notification where notification.notification_condition = :notificationCondition", nativeQuery = true)
    List<String> findEmailsByNotification(@Param("notificationCondition") String notificationCondition);
}