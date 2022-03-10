package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class UserStatusChangerServiceTest {

    public static final Long USER_ID =1L;
    public static final User USER = new User(USER_ID,"user1@email.com","password1",BigDecimal.valueOf(1050.0D),UserStatus.SILVER);


    @Mock
    private UsersRepository usersRepository;
    @InjectMocks
    private UsersRegistrationService service;

    @Test
    @EnumSource(value = UserStatus.class, names = {"NEW_USER","SILVER", "GOLD","PLATINUM"},mode = EnumSource.Mode.INCLUDE)
    void should_change_user_status(){

    var user = usersRepository.findByUserId(USER_ID).get();

        Assertions.assertTrue(user.getUserStatus().equals(UserStatus.SILVER));

    }

}
