//package com.example.shelflife.controllers;
//
//import com.example.shelflife.services.impl.EmailService;
//import com.example.shelflife.services.impl.PushNotificationService;
//import org.springframework.scheduling.TaskScheduler;
//
//import java.util.Date;
//
//public class NotificationTask {
//    private final TaskScheduler taskScheduler;
//    private final EmailService emailService;
//    private final PushNotificationService pushNotificationService;
//
//    public NotificationTask(TaskScheduler taskScheduler, EmailService emailService, PushNotificationService pushNotificationService) {
//        this.taskScheduler = taskScheduler;
//        this.emailService = emailService;
//        this.pushNotificationService = pushNotificationService;
//    }
//
//    public void scheduleNotification(Date date, String email, String token, String message) {
//        Runnable task = () -> {
//            emailService.sendSimpleMessage(email, "Thông báo", message);
//            try {
//                pushNotificationService.sendNotification(token, "Thông báo", message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        };
//
//        taskScheduler.schedule(task, date);
//    }
//}
