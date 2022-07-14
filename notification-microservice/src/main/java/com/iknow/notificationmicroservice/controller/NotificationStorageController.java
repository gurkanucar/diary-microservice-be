package com.iknow.notificationmicroservice.controller;

import com.iknow.notificationmicroservice.model.Notification;
import com.iknow.notificationmicroservice.service.NotificationStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notification")
@RestController
public class NotificationStorageController {

    private final NotificationStorageService notifService;


    public NotificationStorageController(NotificationStorageService notifService) {
        this.notifService = notifService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<List<Notification>> getNotificationsByUserID(@PathVariable String userID) {
        return ResponseEntity.ok(notifService.getNotificationsByUserID(userID));
    }

    @PostMapping
    public ResponseEntity<Notification> getNotificationsByUserID(@RequestBody Notification notification) {
        return ResponseEntity.ok(notifService.createNotificationStorage(notification));
    }

    @PatchMapping("/read/{notifID}")
    public ResponseEntity changeNotifStatusToRead(@PathVariable String notifID) {
        return ResponseEntity.ok(notifService.changeNotifStatusToRead(notifID));
    }


}
