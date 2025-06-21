package com.dyno.Saanjha.model;

import com.dyno.Saanjha.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Data
@Scope("prototype")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NotificationType type;

    @Column(columnDefinition = "TEXT")
    private String message;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User createdBy;






}
