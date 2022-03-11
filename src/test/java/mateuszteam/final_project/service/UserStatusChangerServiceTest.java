package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.UsersRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class UserStatusChangerServiceTest {

    public static final Long USER_ID = 1L;
    public static final User USER = new User(USER_ID, "user1@email.com", "password1", BigDecimal.valueOf(1050.0D), UserStatus.SILVER);

    @Mock
    private UsersRepository usersRepository;
    @Mock
    private UsersMapStructMapper mapper;

    @InjectMocks
    private UserStatusChangerService changerService;

    @Test
    void should_change_user_status() {

    }

    @BeforeAll
    void initializeData() {

        var user1 = User.builder()
                .email("user1@email.com")
                .password("password1")
                .moneySpent(BigDecimal.valueOf(1050.0D))
                .userStatus(UserStatus.SILVER)
                .build();

        var user2 = User.builder()
                .email("user2@email.com")
                .password("password2")
                .moneySpent(BigDecimal.valueOf(6000.0D))
                .userStatus(UserStatus.GOLD)
                .build();

        var user3 = User.builder()
                .email("user3@email.com")
                .password("password3")
                .moneySpent(BigDecimal.valueOf(16000.0D))
                .userStatus(UserStatus.PLATINUM)
                .build();
        var address1 = Address.builder()
                .fullName("First user")
                .addressLine1("Street 356")
                .addressLine2("05-545 Warsaw")
                .phone("555-999-777")
                .user(user1)
                .build();

        var address2 = Address.builder()
                .fullName("Second user")
                .addressLine1("Avenue 116")
                .addressLine2("15-533 Krakow")
                .phone("111-444-555")
                .user(user2)
                .build();

        var address3 = Address.builder()
                .fullName("Third user")
                .addressLine1("King Plaza 116")
                .addressLine2("46-893 Wroclaw")
                .phone("333-477-565")
                .user(user3)
                .build();



    }
}
