package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService {

    private List<Long> movieIds = new ArrayList<>();

    private final OrderPriceCalculator priceCalculator;
    private final MoviesRepository moviesRepository;
    private final MoviesCopiesRepository copiesRepository;

    public List<Long> addMovie(Long movieId) {
        if(! movieIds.contains(movieId) ) {
            movieIds.add(movieId);
        }

        return getCartEntries();
    }

    public List<Long> getCartEntries() {
        return Collections.unmodifiableList(movieIds);
    }

    public MoviesOrder toOrder() {
        var orderedCopies = getCartEntries().stream()
                .map(this::getForMovieId)
                .collect(Collectors.toList());


        //ustalic, czy sa dostepne wolne kopie dla wybranych filmow

        //jesli tak, wyliczamy cene pricePerDay na podstawie filmow oraz dodajemy kopie i zwracamy Order

        //jesli nie, modyfikujemy koszyk i zwracamy uzytkownikowi zamowienie ze zmodyfikowanego koszyka

        return null;
    }

    private MovieCopy getForMovieId(Long movieId) {
        var copyOptional = copiesRepository.findAllByMovie_movieId(movieId).stream()
                .filter(copy -> copy.getMoviesOrder() == null)
                .findFirst();

        var copyOptional2 = copiesRepository.findOneByMovie_movieIdAndMoviesOrder_OrderIdIsNull(movieId);

        return null;
    }

}
