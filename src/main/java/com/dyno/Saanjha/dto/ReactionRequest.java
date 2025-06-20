package com.dyno.Saanjha.dto;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
@NoArgsConstructor
@AllArgsConstructor
public class ReactionRequest {
    private String userId;
    private Long postId;
    private Integer status;
}
