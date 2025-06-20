package com.dyno.Saanjha.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Data
@Component
@Scope("prototype")
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String content;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy HH:mm z")
    private ZonedDateTime createdAt;
}
