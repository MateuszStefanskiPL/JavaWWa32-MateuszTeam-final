package mateuszteam.final_project.domain.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressDto {

    @NotNull
    @Setter(AccessLevel.NONE)
    private Long addressId;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    private String fullName;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    private String addressLine2;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    private String street;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 50)
    private String phone;


}
