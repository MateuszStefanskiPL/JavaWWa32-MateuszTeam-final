package mateuszteam.final_project.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mateuszteam.final_project.domain.dao.ShippingData;
import mateuszteam.final_project.domain.dao.UserStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    private UserStatus userStatus;
}
