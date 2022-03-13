package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.domain.events.OrderPlacedEvent;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.OrdersRepository;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class UserStatusChangerService { //todo: refactor + test analogiczny jak dla MovieChangerService

    private static final BigDecimal SCORE_FOR_SILVER_STATUS = BigDecimal.valueOf(1000D);
    private static final BigDecimal SCORE_FOR_GOLD_STATUS = BigDecimal.valueOf(5000D);
    private static final BigDecimal SCORE_FOR_PLATINUM_STATUS = BigDecimal.valueOf(10000D);

    private UsersRepository usersRepository;
    private UsersMapStructMapper mapper;
    private OrdersRepository ordersRepository;


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
    void handleOrderPlacedEvent(OrderPlacedEvent event) throws Exception {
        this.saveChangedUsers(event.getUserEmail());
    }

}

// todo poddać review zmiany w klasie userstatuschanger i narzucony na niego Event Listner
//  i zmiany w klasie MoviesStatusChangerService i UserStatusChangerServiceTest

//todo  pododawać role użytkowników , napisać pozostałe testy , wyłapać resztę exceptionów ( może trener by zrobił ściągę ->
// todo jakie jeszcze mogą być wyjątki a my je znajdziemy i obhandlujemy

//todo przydała by też się sciąga jakich testów użyć do testowania danej klasy
//todo np AClassService - użyjcie moków i JpaTest , BclassService bez moków tylko zwykle testy








