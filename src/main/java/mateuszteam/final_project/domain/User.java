package mateuszteam.final_project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty
    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 1000)
    @Column(name = "password")
    private String password;

    @Embedded
    private ShippingData shippingData;

    @NotNull
    @Column(name = "user_status")
    private UserStatus userStatus;


}


