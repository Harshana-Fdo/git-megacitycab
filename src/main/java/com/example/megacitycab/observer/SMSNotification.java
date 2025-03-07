package com.example.megacitycab.observer;

public class SMSNotification implements Observer{

    @Override
    public void update(String message) {
        System.out.println("📱 SMS Notification Sent: " + message);
    }
}
