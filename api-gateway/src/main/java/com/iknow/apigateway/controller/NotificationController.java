package com.iknow.apigateway.controller;

import com.iknow.apigateway.model.Notification;
import com.iknow.apigateway.requestService.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notification")
@RestController
public class NotificationController {

    private final NotificationService notifService;

    public NotificationController(NotificationService notifService) {
        this.notifService = notifService;
    }

    @GetMapping("/{userID}")
    public ResponseEntity<List<Notification>> getNotificationsByUserID(@PathVariable String userID) {
        return ResponseEntity.ok(notifService.getNotificationsByUserID(userID));
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notifService.createNotificationStorage(notification));
    }

    @PatchMapping("/read/{notifID}")
    public ResponseEntity<Notification> changeNotifStatusToRead(@PathVariable String notifID) {
        return ResponseEntity.ok(notifService.changeNotifStatusToRead(notifID));
    }


}
