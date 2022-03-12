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
public class UserStatusChangerService {

    private static final BigDecimal SCORE_FOR_SILVER_STATUS = BigDecimal.valueOf(1000D);
    private static final BigDecimal SCORE_FOR_GOLD_STATUS = BigDecimal.valueOf(5000D);
    private static final BigDecimal SCORE_FOR_PLATINUM_STATUS = BigDecimal.valueOf(10000D);

    private final UsersRepository usersRepository;
    private final UsersMapStructMapper mapper;
    private final OrdersRepository ordersRepository;

    public void saveChangedUsers() {
        List<User> changedUsers = changeUsersStatus().stream()
                .map(mapper::mapFromDtoToDomain)
                .collect(Collectors.toList());
        usersRepository.saveAll(changedUsers);
    }

    private List<UserDto> changeUsersStatus() {
        return usersRepository.findAll().stream()
                .map(mapper::mapFromDomainToDto)
                .map(this::changeUserStatus)
                .collect(Collectors.toList());

    }

    private UserDto changeUserStatus(UserDto user) {

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

    private void changeStatus(UserDto user, UserStatus status) {
        user.setUserStatus(status);
    }

    @EventListener
    void handleOrderPlacedEvent(OrderPlacedEvent event) throws Exception {
        this.saveChangedUsers();
    }

}

// todo poddać review zmiany w klasie userstatuschanger i narzucony na niego Event Listner i zmiany w klasie MoviesStatusChangerService
//todo  pododawać role użytkowników , napisać testy , wyłapać resztę exceptionów ( może trener by zrobił ściągę
// todo jakie jeszcze mogą być wyjątki a my je znajdziemy i obhandlujemy
//todo przydała by też się sciąga jakich testów użyć do testowania danej klasy
//todo np AClassService - użyjcie moków i JpaTest , BclassService bez moków tylko zwykle testy








