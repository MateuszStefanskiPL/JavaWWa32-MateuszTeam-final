package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.domain.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void userFromAddressShouldBeTheSameAsUser() {
        //given
        User user = User.builder()
                .email("barbara@wp.pl")
                .password("psw")
                .build();

        Address address = Address.builder()
                .fullName("Basia Jasna")
                .addressLine1("Warszawska 5")
                .addressLine2("Zielonka")
                .phone("567456928")
                .user(user)
                .build();

        //when
        addressRepository.save(address);
        Assertions.assertThat(address.getId()).isNotNull();

        var foundAddressOptional = addressRepository.findById(address.getId());

        //then
        Assertions.assertThat(foundAddressOptional.isPresent()).isTrue();
        var savedAddress = foundAddressOptional.get();
        Assertions.assertThat(savedAddress.getUser()).isNotNull();
        Assertions.assertThat(savedAddress.getUser().getUserId()).isEqualTo(user.getUserId());

    }

}