package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import mateuszteam.final_project.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Component
public class OrderPriceCalculator {

    private static final BigDecimal STANDARD_MOVIE_PRICE = BigDecimal.valueOf(10);

    private static final BigDecimal SILVER_USER_DISCOUNT = BigDecimal.valueOf(0.95D);
    private static final BigDecimal GOLD_USER_DISCOUNT = BigDecimal.valueOf(0.85D);
    private static final BigDecimal PLATINUM_USER_DISCOUNT = BigDecimal.valueOf(0.70D);

    private static final BigDecimal PREMIERE_MOVIE_PRICE_IMPACT = BigDecimal.valueOf(3.0D);
    private static final BigDecimal NEWEST_MOVIE_PRICE_IMPACT = BigDecimal.valueOf(2.0D);
    private static final BigDecimal CLASSIC_MOVIE_PRICE_IMPACT = BigDecimal.valueOf(0.5D);


    private final OrdersRepository ordersRepository;
    private final MoviesCopiesRepository copiesRepository;

    public BigDecimal calculateTotalOrderPrice(){
        //trzeba pomnożyć cenę za dzień razy ilość dni
        return null;
    }

    public void setPricePerDayAfterDiscount(MoviesOrder order){
        var pricePerDay = calculateOrderPricePerDay(order);
        order.setPricePerDay(pricePerDay);
        log.info("Price per day for order nr " + order.getOrderId() + "has been set to " + pricePerDay);
    }

    private BigDecimal calculateOrderPricePerDay(MoviesOrder order) {
        var userStatus = order.getUser().getUserStatus();
        var movieStatus = order.getMovieCopies().stream().findFirst().get().getMovie().getMovieStatus();
        var pricePerDay = calculateOrderPriceByMovieStatus(movieStatus, userStatus);

        return pricePerDay;
    }

    private BigDecimal calculateOrderPriceByUserStatus(UserStatus userStatus) {
        var finalPrice = STANDARD_MOVIE_PRICE;
        switch (userStatus) {
            case SILVER:
                finalPrice = STANDARD_MOVIE_PRICE.multiply(SILVER_USER_DISCOUNT);
                break;
            case GOLD:
                finalPrice = STANDARD_MOVIE_PRICE.multiply(GOLD_USER_DISCOUNT);
                break;
            case PLATINUM:
                finalPrice = STANDARD_MOVIE_PRICE.multiply(PLATINUM_USER_DISCOUNT);
                break;
            default:
                finalPrice = STANDARD_MOVIE_PRICE;
        }
        return finalPrice;
    }

    private BigDecimal calculateOrderPriceByMovieStatus(MovieStatus movieStatus, UserStatus userStatus) {
        var finalPrice = calculateOrderPriceByUserStatus(userStatus);

        switch (movieStatus) {
            case CLASSIC:
                finalPrice.multiply(CLASSIC_MOVIE_PRICE_IMPACT);
                break;
            case NEWEST:
                finalPrice.multiply(NEWEST_MOVIE_PRICE_IMPACT);
                break;
            case PREMIERE:
                finalPrice.multiply(PREMIERE_MOVIE_PRICE_IMPACT);
                break;
        }
        return finalPrice;
    }


}
//todo jak zorganizować obliczanie czasu oddania skoro
// tylko zmieniamy status i nie ma jak pobrać daty oddania jedynie może eventem przy kazdej
// zmianie statusu aktualizować cene
