//package com.example.shelflife.controllers;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//public class NotificationController {
//
//    private final NotificationTask notificationTask;
//
//    public NotificationController(NotificationTask notificationTask) {
//        this.notificationTask = notificationTask;
//    }
//
//    @PostMapping("/schedule-notification")
//    public void scheduleNotification(@RequestBody NotificationRequest request) {
//        notificationTask.scheduleNotification(request.getDate(), request.getEmail(), request.getToken(), request.getMessage());
//    }
//
//    public static class NotificationRequest {
//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//        private Date date;
//        private String email;
//        private String token;
//        private String message;
//
//        // getters
//        public Date getDate() {
//            return date;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        // setters
//        public void setDate(Date date) {
//            this.date = date;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }
//}
