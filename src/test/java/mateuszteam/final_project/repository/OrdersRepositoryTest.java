package mateuszteam.final_project.repository;


import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.domain.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
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
                .user(user)
                .build();

        Optional<MoviesOrder> foundOrderOptional = ordersRepository.findById(order.getOrderId());
        Assertions.assertThat(foundOrderOptional.isEmpty()).isTrue();

        //when
        usersRepository.save(user);
        ordersRepository.save(order);

        Assertions.assertThat(order.getOrderId()).isNotNull();

        MoviesOrder foundOrder = ordersRepository.findById(order.getOrderId()).get();
        //then
        Assertions.assertThat(foundOrderOptional.isPresent());
        Assertions.assertThat(foundOrder.getUser()).isNotNull();
        Assertions.assertThat(foundOrder.getUser().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void retrieves_paged_orders_for_user() {
        //given
        User user = User.builder()
                .email("email@email.com")
                .password("password")
                .build();
        var o1 = MoviesOrder.builder()
                .orderStatus(OrderStatus.ACCEPTED)
                .orderPlacedDate(LocalDateTime.now())
                .totalPrice(BigDecimal.valueOf(5))
                .user(user)
                .build();
        var o2 = MoviesOrder.builder()
                .orderStatus(OrderStatus.TURNED)
                .orderPlacedDate(LocalDateTime.now().minusDays(15))
                .totalPrice(BigDecimal.valueOf(15))
                .user(user)
                .build();
        var o3 = MoviesOrder.builder()
                .orderStatus(OrderStatus.TURNED)
                .orderPlacedDate(LocalDateTime.now().minusDays(30))
                .totalPrice(BigDecimal.valueOf(30))
                .user(user)
                .build();
        var o4 = MoviesOrder.builder()
                .orderStatus(OrderStatus.TURNED)
                .orderPlacedDate(LocalDateTime.now().minusDays(45))
                .totalPrice(BigDecimal.valueOf(45))
                .user(user)
                .build();
        var orders = Arrays.asList(o1, o2, o3, o4);
        ordersRepository.saveAll(orders);

        //when
        var page1 = PageRequest.of(0, 2).withSort(Sort.by(Sort.Direction.DESC, "orderPlacedDate"));
        var page2 = PageRequest.of(1, 2).withSort(Sort.by(Sort.Direction.DESC, "orderPlacedDate"));

        Assertions.assertThat(user.getUserId()).isNotNull();
        var ordersForPage1 = ordersRepository.findByUser_userId(user.getUserId(), page1);
        var ordersForPage2 = ordersRepository.findByUser_userId(user.getUserId(), page2);

        //then
        Assertions.assertThat(ordersForPage1).isNotNull();
        Assertions.assertThat(ordersForPage1.getTotalPages()).isEqualTo(2);
        Assertions.assertThat(ordersForPage1.getTotalElements()).isEqualTo(4);
        var orderPage1 = ordersForPage1.getContent();
        Assertions.assertThat(orderPage1).hasSize(2);
        var ro1 = orderPage1.get(0);    //retrieved o1
        Assertions.assertThat(ro1).isNotNull();
        Assertions.assertThat(ro1.getTotalPrice()).isEqualTo(BigDecimal.valueOf(5));
        var ro4 = ordersForPage2.getContent().get(1);   //o4
        Assertions.assertThat(ro4).isNotNull();
        Assertions.assertThat(ro4.getTotalPrice()).isEqualTo(BigDecimal.valueOf(45));
    }


}
