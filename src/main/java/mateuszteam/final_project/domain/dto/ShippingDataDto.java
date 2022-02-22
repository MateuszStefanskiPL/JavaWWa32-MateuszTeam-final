package mateuszteam.final_project.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class ShippingDataDto {

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    private String nameAndSurname;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 100)
    private String postCode;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 100)
    private String city;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    private String street;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 50)
    private String phone;
}
