package com.iknow.apigateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    private String id;

    private String content;

    private User userTo;

    private User userFrom;

    private NotificationType notificationType;

    private boolean delivered;
    private boolean read;

}
