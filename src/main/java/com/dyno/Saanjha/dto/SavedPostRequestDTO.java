package com.dyno.Saanjha.dto;


import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SavedPostRequestDTO {
    private String userId;
    private Long postId;
}
