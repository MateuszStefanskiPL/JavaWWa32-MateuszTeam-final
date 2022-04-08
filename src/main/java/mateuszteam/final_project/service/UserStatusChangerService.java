package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.domain.events.OrderReturnedEvent;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class UserStatusChangerService {

    private static final BigDecimal SCORE_FOR_SILVER_STATUS = BigDecimal.valueOf(1000D);
    private static final BigDecimal SCORE_FOR_GOLD_STATUS = BigDecimal.valueOf(5000D);
    private static final BigDecimal SCORE_FOR_PLATINUM_STATUS = BigDecimal.valueOf(10000D);

    private final UsersRepository usersRepository;

    public void saveChangedUsers(String userEmail) {
        var user = usersRepository.findByEmail(userEmail).get();
        changeUserStatus(user);
        usersRepository.save(user);
    }

    //nie uzywana metoda, poniewaz powyzej aktualizujemy status uzytkownika po kazdym zamowieniu
    private List<User> changeUsersStatus() {
        return usersRepository.findAll().stream()
                .map(this::changeUserStatus)
                .collect(Collectors.toList());
    }

    public User changeUserStatus(User user) {

        if (user.getMoneySpent().compareTo(SCORE_FOR_PLATINUM_STATUS) > 0) {
            changeStatus(user, UserStatus.PLATINUM);
        } else if (user.getMoneySpent().compareTo(SCORE_FOR_GOLD_STATUS) > 0) {
            changeStatus(user, UserStatus.GOLD);
        } else if (user.getMoneySpent().compareTo(SCORE_FOR_SILVER_STATUS) > 0) {
            changeStatus(user, UserStatus.SILVER);
        } else {
            changeStatus(user, UserStatus.NEW_USER);
        }
        log.info("User " + user.getEmail() + " status has been changed on " + user.getUserStatus().toString());

        return user;
    }

    private void changeStatus(User user, UserStatus status) {
        user.setUserStatus(status);
    }

    @EventListener
    public void handleOrderReturnedEvent(OrderReturnedEvent event) throws Exception {
        this.saveChangedUsers(event.getUserEmail());
    }

}









