package com.iknow.apigateway.requestService;

import com.iknow.apigateway.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@FeignClient(value = "notification-microservice",
        path = "",
        configuration = FeignConfiguration.class)
public interface NotificationService {


    @GetMapping("/push-notifications/{userID}")
    Flux<ServerSentEvent<List<Notification>>> streamLastMessage(@PathVariable String userID);


    @GetMapping("/notification/{userID}")
    ResponseEntity<List<Notification>> getNotificationsByUserID(@PathVariable String userID);

    @PostMapping("/notification")
    ResponseEntity<Notification> getNotificationsByUserID(@RequestBody Notification notification);

    @PatchMapping("/notification/read/{notifID}")
    ResponseEntity<Notification> changeNotifStatusToRead(@PathVariable String notifID);

}
