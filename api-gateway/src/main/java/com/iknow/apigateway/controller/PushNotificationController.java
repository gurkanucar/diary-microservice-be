package com.iknow.apigateway.controller;

import com.iknow.apigateway.model.Notification;
import com.iknow.apigateway.requestService.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/push-notifications")
@Slf4j
public class PushNotificationController {

    private final NotificationService service;

    public PushNotificationController(NotificationService service) {
        this.service = service;
    }


    @GetMapping("/{userID}")
    public Flux<ServerSentEvent<List<Notification>>> streamLastMessage(@PathVariable String userID) {
        return service.streamLastMessage(userID);
    }

}
