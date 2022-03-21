package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class OrderPriceCalculatorTest {

    private OrderPriceCalculator calculator = new OrderPriceCalculator();

    @Test
    void should_calculate_order_price_per_day() {
        //given
        var user1 = User.builder()
                .email("user1@email.com")
                .password("password1")
                .moneySpent(BigDecimal.valueOf(1050.0D))
                .userStatus(UserStatus.SILVER)
                .build();

        var movie1 = Movie.builder()
                .title("A Nightmare on Elm Street")
                .releaseDate(LocalDate.of(1984, 11, 9))
                .director("Wes Craven")
                .genre(Genre.HORROR)
                .starring("Elm Street in provincial Springwood is inhabited by 15-year-old Tina Gray (Amanda Wyss) - a girl who is haunted by nightmares. Their anti-hero is a man in a red and green sweater and an old hat, a scarred face with burns, and armed with a glove with four blades. The dreams become more and more realistic and one night Tina wakes up screaming to discover that her nightgown has been cut, just like in her dream.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(10.0D)
                .build();

        var userStatus = user1.getUserStatus();
        var movieStatus = movie1.getMovieStatus();

        //when

        var price = calculator.calculatePricePerDay(movieStatus, userStatus);

        //then

        Assertions.assertThat(price.doubleValue()).isEqualTo(4.75);

    }


}
