package com.iknow.apigateway.requestService;

import com.iknow.apigateway.model.Notification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import java.util.List;

@FeignClient(value = "notification-microservice",
        path = "/notification",
        configuration = FeignConfiguration.class)
public interface NotificationService {



    @GetMapping("/{userID}")
    public Flux<ServerSentEvent<List<Notification>>> streamLastMessage(@PathVariable String userID);


}
