package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Component
public class UserStatusChanger {

    private static final BigDecimal SCORE_FOR_SILVER_STATUS = BigDecimal.valueOf(1000D);
    private static final BigDecimal SCORE_FOR_GOLD_STATUS = BigDecimal.valueOf(5000D);
    private static final BigDecimal SCORE_FOR_PLATINUM_STATUS = BigDecimal.valueOf(10000D);

    private final UsersRepository usersRepository;

    public void changeUserStatus(){

        var allUsers = usersRepository.findAll();

        for (var user : allUsers){
            if (user.getMoneySpent().compareTo(SCORE_FOR_PLATINUM_STATUS) > 0){
                user.setUserStatus(UserStatus.PLATINUM);
            }
            else if(user.getMoneySpent().compareTo(SCORE_FOR_GOLD_STATUS) > 0){
                user.setUserStatus(UserStatus.GOLD);
            }
            else if(user.getMoneySpent().compareTo(SCORE_FOR_SILVER_STATUS) > 0){
                user.setUserStatus(UserStatus.SILVER);
            }
            else {
                user.setUserStatus(UserStatus.NEW_USER);
            }
            log.info("User " + user.getEmail() + " status has been changed on " + user.getUserStatus().toString());
        }

    }


}

//    Typ klienta Liczba wypożyczeń
//        (filmów, nie zamówień!)
//    Wydane pieniądze Wpływ na cenę
//        wypożyczenia
//    Silver 25 1000 0,95 x (5% zniżki)
//        Gold 100 5000 0,85% (15%)
//        Platinum 200 10000 0,70% (30%)

//todo zapiac na evencie i napisać testy dla tej klasy
