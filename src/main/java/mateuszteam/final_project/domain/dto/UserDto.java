package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.domain.entities.UserStatus;

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
    private Address address;

    @NotEmpty
    @NotNull
    private UserStatus userStatus;


}
