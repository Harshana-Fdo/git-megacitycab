package com.example.megacitycab.observer;

public class EmailNotification implements Observer {
    @Override
    public void update(String message) {
        System.out.println("📧 Email Notification Sent: " + message);
    }
}
