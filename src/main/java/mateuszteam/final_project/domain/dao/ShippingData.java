package mateuszteam.final_project.domain.dao;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShippingData {

    @Column(name = "name")
    private String nameAndSurname;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "phone")
    private String phone;


}
