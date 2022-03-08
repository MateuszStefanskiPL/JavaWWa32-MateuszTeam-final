package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService {

    private List<Long> movieIds = new ArrayList<>();

    private final OrderPriceCalculator priceCalculator;
    private final MoviesCopiesRepository copiesRepository;

    public List<Long> addMovie(Long movieId) {
        if (!movieIds.contains(movieId)) {
            movieIds.add(movieId);
        }
        return getCartEntries();
    }

    public List<Long> removeMovie(final Long movieId) {
        if (movieIds.contains(movieId)) {
            movieIds.remove(movieId);
        }
        return getCartEntries();
    }

    public List<Long> getCartEntries() {
        return Collections.unmodifiableList(movieIds);
    }

    public MoviesOrder toOrder() {
        var orderedCopies = getCartEntries().stream()
                .map(this::getFreeCopyForMovieId)
                .collect(Collectors.toSet());

        var order = MoviesOrder.builder().build();

        if (!orderedCopies.isEmpty()){
            order.setMovieCopies(orderedCopies);
            priceCalculator.setPricePerDayAfterDiscount(order);
            order.setOrderPlacedDate(LocalDateTime.now());
            //todo skąd wziąc dane o userze ? security ?
        }



        //ustalic, czy sa dostepne wolne kopie dla wybranych filmow

        //jesli tak, wyliczamy cene pricePerDay na podstawie filmow oraz dodajemy kopie i zwracamy Order

        //jesli nie, modyfikujemy koszyk i zwracamy uzytkownikowi zamowienie ze zmodyfikowanego koszyka

        return order;
    }

    private MovieCopy getFreeCopyForMovieId(Long movieId) {
        var copyOptional = copiesRepository.findAllByMovie_movieId(movieId).stream()
                .filter(copy -> copy.getMoviesOrder() == null)
                .findFirst();

        //var copyOptional2 = copiesRepository.findOneByMovie_movieIdAndMoviesOrder_OrderIdIsNull(movieId);

        return copyOptional.get();
    }


}
