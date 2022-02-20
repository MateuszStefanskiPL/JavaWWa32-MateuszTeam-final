package mateuszteam.final_project.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class ShippingData {

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    @Column(name = "name")
    private String nameAndSurname;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "post_code")
    private String postCode;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "city")
    private String city;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 250)
    @Column(name = "street")
    private String street;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "phone")
    private String phone;


}
