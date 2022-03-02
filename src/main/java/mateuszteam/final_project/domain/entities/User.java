package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private Long userId;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private BigDecimal moneySpent;

    @Enumerated
    @Column(name = "status")
    private UserStatus userStatus;

}


