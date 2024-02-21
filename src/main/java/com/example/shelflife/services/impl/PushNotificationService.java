//package com.example.shelflife.services.impl;
//
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.Message;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PushNotificationService {
//    public void sendNotification(String targetToken, String title, String body) throws Exception {
//        Message message = Message.builder()
//                .putData("title", title)
//                .putData("body", body)
//                .setToken(targetToken)
//                .build();
//
//        FirebaseMessaging.getInstance().send(message);
//    }
//}
