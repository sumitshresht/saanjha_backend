package com.dyno.Saanjha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Scope("prototype")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "saves")
public class Save {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne()
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

}
