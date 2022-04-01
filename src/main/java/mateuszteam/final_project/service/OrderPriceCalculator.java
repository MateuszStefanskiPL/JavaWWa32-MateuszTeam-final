package mateuszteam.final_project.service;

import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.domain.events.OrderReturnedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrderPriceCalculator {

    private static final BigDecimal STANDARD_MOVIE_PRICE = BigDecimal.valueOf(10);

    private static final BigDecimal SILVER_USER_DISCOUNT = BigDecimal.valueOf(0.95D);
    private static final BigDecimal GOLD_USER_DISCOUNT = BigDecimal.valueOf(0.85D);
    private static final BigDecimal PLATINUM_USER_DISCOUNT = BigDecimal.valueOf(0.70D);

    private static final BigDecimal PREMIERE_MOVIE_PRICE_IMPACT = BigDecimal.valueOf(3.0D);
    private static final BigDecimal NEWEST_MOVIE_PRICE_IMPACT = BigDecimal.valueOf(2.0D);
    private static final BigDecimal CLASSIC_MOVIE_PRICE_IMPACT = BigDecimal.valueOf(0.5D);

    public BigDecimal calculateTotalOrderPrice(){
        //trzeba pomnożyć cenę za dzień razy ilość dni
        return null;
    }


    //todo--question-- tu chyba trzeba obrócić jakąś pętlą żeby policzyło cenę za wszystkie kopie a nie tylko za jedną
    public BigDecimal calculateOrderPricePerDay(MoviesOrder order) {
        var userStatus = order.getUser().getUserStatus();
        var movieStatus = order.getMovieCopies().stream().findFirst().get().getMovie().getMovieStatus();
        var pricePerDay = calculatePricePerDay(movieStatus, userStatus);
        order.setPricePerDay(pricePerDay);
        return pricePerDay;
    }

    BigDecimal calculatePricePerDay(List<MovieStatus> movieStatus, UserStatus userStatus) {
        var pricePerDayFactorForUserStatus = calcOrderPricePerDayFactor(userStatus);
        var pricePerDayFactorForMovieStatus = calcOrderPricePerDayFactor(movieStatus);

        return STANDARD_MOVIE_PRICE
                .multiply(pricePerDayFactorForUserStatus
                        .multiply(pricePerDayFactorForMovieStatus));
    }

    //factor => czynnik
    private BigDecimal calcOrderPricePerDayFactor(UserStatus userStatus) {
        switch (userStatus) {
            case SILVER:
                return SILVER_USER_DISCOUNT;
            case GOLD:
                return GOLD_USER_DISCOUNT;
            case PLATINUM:
                return PLATINUM_USER_DISCOUNT;
            default:
                return BigDecimal.ONE;
        }
    }

    private BigDecimal calcOrderPricePerDayFactor(MovieStatus movieStatus) {
        switch (movieStatus) {
            case CLASSIC:
                return CLASSIC_MOVIE_PRICE_IMPACT;
            case NEWEST:
                return NEWEST_MOVIE_PRICE_IMPACT;
            case PREMIERE:
                return PREMIERE_MOVIE_PRICE_IMPACT;
            case STANDARD:
                return BigDecimal.ONE;
            default:    //i tak nigdy nie osiagnie tego miejsca
                return BigDecimal.ONE;
        }
    }

}
