package mateuszteam.final_project.repository;


import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.domain.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@DataJpaTest
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void userFromOrderShouldBeTheSameAsUser(){
        //given

        User user = User.builder()
                .email("email@email.com")
                .password("password")
                .build();

        Optional<User> foundUserOptional = usersRepository.findByEmail(user.getEmail());
        Assertions.assertThat(foundUserOptional.isEmpty()).isTrue();

        MoviesOrder order = MoviesOrder.builder()
                .movieCopies(new HashSet<MovieCopy>())
                .orderStatus(OrderStatus.ACCEPTED)
                .orderPlacedDate(LocalDateTime.now())
                .build();

        Optional<MoviesOrder> foundOrderOptional = ordersRepository.findById(order.getOrderId());
        Assertions.assertThat(foundOrderOptional.isEmpty()).isTrue();

        //when
        usersRepository.save(user);
        ordersRepository.save(order);

        MoviesOrder foundOrder = ordersRepository.findById(1L).get();
        //then
        Assertions.assertThat(foundOrderOptional.isEmpty()).isFalse();

    }

}
