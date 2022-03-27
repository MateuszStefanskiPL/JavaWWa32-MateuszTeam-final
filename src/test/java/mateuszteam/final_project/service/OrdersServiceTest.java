package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.mapper.OrdersMapStructMapper;
import mateuszteam.final_project.repository.OrdersRepository;
import mateuszteam.final_project.repository.UsersRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
class OrdersServiceTest {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    OrdersService service;

    @Autowired
    OrdersMapStructMapper ordersMapper;

    @Autowired
    SessionCartService cartService;

    @Autowired
    OrderPriceCalculator priceCalculator;


    @Test
    void should_remove_not_accepted_orders() {


        //given

        User user = User.builder()
                .email("email@email.com")
                .password("password")
                .build();

        Optional<User> foundUserOptional = usersRepository.findByEmail(user.getEmail());
        Assertions.assertThat(foundUserOptional.isEmpty()).isTrue();

        MoviesOrder order = MoviesOrder.builder()
                .movieCopies(new HashSet<MovieCopy>())
                .orderStatus(null)
                .orderPlacedDate(LocalDateTime.now())
                .user(user)
                .build();

        Optional<MoviesOrder> foundOrderOptional = ordersRepository.findById(order.getOrderId());
        Assertions.assertThat(foundOrderOptional.isEmpty()).isTrue();


        ordersRepository.save(order);

        foundOrderOptional = ordersRepository.findById(order.getOrderId());
        Assertions.assertThat(foundOrderOptional.isPresent());

        var userFound = foundOrderOptional.get().getUser();

        Assertions.assertThat(userFound.getEmail()).isEqualTo(user.getEmail());

        Assertions.assertThat(ordersRepository.findAll().size()).isEqualTo(1);

        //when

        service.removeNotAcceptedOrders();

        //then

        Assertions.assertThat(ordersRepository.findAll().size()).isEqualTo(0);
        Assertions.assertThat(ordersRepository.findAll()).isEmpty();


    }

    @Test
    void should_place_order_from_cart(){
        //when
        var cartToOrder = MoviesOrder.builder()
                .build();
        var user = User.builder()
                .email("email@email.com")
                .password("password")
                .userStatus(UserStatus.NEW_USER)
                .build();

        //todo --question-- jak to zrobić?
    }
}
