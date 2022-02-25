package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.dao.ShippingData;
import mateuszteam.final_project.domain.dao.UserStatus;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {



    @Setter(AccessLevel.NONE)
    @NotNull
    private Long userId;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 1000)
    private String password;

    @NotNull
    private ShippingData shippingData;

    @NotEmpty
    @NotNull
    private UserStatus userStatus;


}
