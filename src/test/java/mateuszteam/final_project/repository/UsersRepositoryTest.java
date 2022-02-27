package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @Test
    void shouldSaveUser(){
        //given
        User user = User.builder()
                .email("email@email.com")
                .password("password")
                .build();

        Optional<User> foundUserOptional = usersRepository.findByEmail(user.getEmail());
        Assertions.assertThat(foundUserOptional.isEmpty()).isTrue();

        //when
        usersRepository.save(user);

        foundUserOptional = usersRepository.findByEmail("email@email.com");


        //then

        Assertions.assertThat(foundUserOptional.isEmpty()).isFalse();
        User foundUser = foundUserOptional.get();
        Assertions.assertThat(foundUser.getPassword()).isEqualTo(user.getPassword());
    }



}
