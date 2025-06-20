package com.dyno.Saanjha.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {
    private Long postId;
    private String postContent;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String userId;
    private String profilePhoto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMM yyyy HH:mm z")
    private ZonedDateTime createdAt;
}

