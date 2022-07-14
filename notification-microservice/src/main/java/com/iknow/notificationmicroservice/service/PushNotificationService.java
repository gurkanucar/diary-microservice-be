package com.iknow.notificationmicroservice.service;

import com.iknow.notificationmicroservice.model.Notification;
import com.iknow.notificationmicroservice.repository.NotificationStorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PushNotificationService {


    private final NotificationStorageRepository notificationStorageRepository;


    public PushNotificationService(NotificationStorageRepository notificationStorageRepository) {
        this.notificationStorageRepository = notificationStorageRepository;
    }


    public Flux<ServerSentEvent<List<Notification>>> getNotificationsByUserToID(String userID) {
        if (userID != null && !userID.isBlank()) {
            return Flux.interval(Duration.ofSeconds(6))
                    .publishOn(Schedulers.boundedElastic())
                    .map(sequence -> ServerSentEvent.<List<Notification>>builder().id(String.valueOf(sequence))
                            .event("notif-list-event").data(notificationStorageRepository.findByUserToIdAndDeliveredFalse(userID))
                            .build());
        }

        return Flux.interval(Duration.ofSeconds(1)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
                .id(String.valueOf(sequence)).event("notif-list-event").data(new ArrayList<>()).build());
    }
}
