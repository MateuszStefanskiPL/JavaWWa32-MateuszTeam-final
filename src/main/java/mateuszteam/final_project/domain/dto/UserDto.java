package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.entities.UserStatus;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Digits(integer = 10, fraction = 2)
    private BigDecimal moneySpent;

    @NotEmpty
    @NotNull
    private UserStatus userStatus;


}
