package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import mateuszteam.final_project.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Component
public class OrderPriceCalculator {

    private final OrdersRepository ordersRepository;
    private final MoviesCopiesRepository copiesRepository;

    public BigDecimal calculateOrderPrice(MoviesOrder order){
        var userStatus = order.getUser().getUserStatus();
        var movieStatus = order.getMovieCopies().stream().findFirst().get().getMovie().getMovieStatus();
        var orderDate = order.getOrderPlacedDate();
        return null;
    }

    //client status
    //movie status
    //order time
//todo jak zorganizować obliczanie czasu oddania skoro
// tylko zmieniamy status i nie ma jak pobrać daty oddania jedynie może eventem przy kazdej
// zmianie statusu aktualizować cene





}
