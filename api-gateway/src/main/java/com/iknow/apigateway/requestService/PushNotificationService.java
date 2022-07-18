package com.iknow.apigateway.requestService;

import com.iknow.apigateway.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "notification-microservice",
        path = "/push-notifications",
        configuration = FeignConfiguration.class)
public interface PushNotificationService {

    @GetMapping("/{userID}")
    public ResponseEntity<List<Notification>> getNotificationsByUserID(@PathVariable String userID);

    @PostMapping
    public ResponseEntity<Notification> getNotificationsByUserID(@RequestBody Notification notification);

    @PatchMapping("/read/{notifID}")
    public ResponseEntity<Notification> changeNotifStatusToRead(@PathVariable String notifID);

}
