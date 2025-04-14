package com.lazarnisic.ParkSmart.service;


public interface NotificationService {
    void sendReservationNotification(String message);
    void sendEmailReservationNotification(String message);
}
