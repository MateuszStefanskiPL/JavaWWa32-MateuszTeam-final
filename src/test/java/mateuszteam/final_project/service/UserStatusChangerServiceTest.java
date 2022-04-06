package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UserStatusChangerServiceTest {

    private UserStatusChangerService service = new UserStatusChangerService(null);

    @Test
    void should_change_user_status() {
        //given

       var user = returnUserDtoForTest();
       //when
        user.setMoneySpent(user.getMoneySpent().add(BigDecimal.valueOf(5000)));
        service.changeUserStatus(user);
        //then
        Assertions.assertEquals(UserStatus.GOLD, user.getUserStatus());


    }


    User returnUserDtoForTest(){

        return  User.builder()
                .userId(1L)
                .email("user1@email.com")
                .password("password1")
                .moneySpent(BigDecimal.valueOf(1050.0D))
                .userStatus(UserStatus.SILVER)
                .build();


    }
}
