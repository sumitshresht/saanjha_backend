package com.dyno.Saanjha.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Scope("prototype")
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private String profilePhoto;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Post> posts;
}
