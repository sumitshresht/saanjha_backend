package com.dyno.Saanjha.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String userId;
    private Long postId;
    private String content;
}
