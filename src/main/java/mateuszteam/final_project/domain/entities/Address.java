package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "address_id")
    private Long addressId;

    private String fullName;
    private String addressLine1;
    private String addressLine2;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
