package com.dyno.Saanjha.dto;

import com.dyno.Saanjha.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

        private String userId;
        private String firstName;
        private String lastName;
        private String email;
        private String profilePhoto;
        private String password;


    public UserDTO(User user) {

            this.userId = user.getUserId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.profilePhoto = user.getProfilePhoto();
            this.password = user.getPassword();

    }
}
