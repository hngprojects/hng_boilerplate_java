package hng_java_boilerplate.profile.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hng_java_boilerplate.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

}
