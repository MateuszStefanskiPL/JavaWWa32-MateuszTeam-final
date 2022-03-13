package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.exceptions.CopiesNotFoundException;
import mateuszteam.final_project.exceptions.DuplicateOrderException;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
//@Scope(value = WebApplicationContext.SCOPE_SESSION,
//        proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
public class SessionCartService {

    private List<Long> movieIds = new ArrayList<>();

    private final MoviesCopiesRepository copiesRepository;
    private final OrderPriceCalculator priceCalculator;

    public List<Long> addMovie(Long movieId) {
        if (!movieIds.contains(movieId)) {
            movieIds.add(movieId);
        }else {
            throw new DuplicateOrderException("You can`t borrow two copies of the same movie");
        }
        return getCartEntries();
    }

    public List<Long> removeMovie(final Long movieId) {
        if (movieIds.contains(movieId)) {
            movieIds.remove(movieId);
        }else {
            throw new ResourceNotFoundException(movieId);
        }
        return getCartEntries();
    }

    public List<Long> getCartEntries() {
        return Collections.unmodifiableList(movieIds);
    }

    //todo test it -> Unit test (metode zasila tylko pole z id-kami)
    public MoviesOrder toOrder() {
        var orderedCopies = getCartEntries().stream()
                .map(this::getFreeCopyForMovieId)
                .collect(Collectors.toSet());

        var order = new MoviesOrder();

        if (!orderedCopies.isEmpty()){
            order.setMovieCopies(orderedCopies);
        }

        //ustalic, czy sa dostepne wolne kopie dla wybranych filmow

        //jesli tak, wyliczamy cene pricePerDay na podstawie filmow oraz dodajemy kopie i zwracamy Order

        //jesli nie, modyfikujemy koszyk i zwracamy uzytkownikowi zamowienie ze zmodyfikowanego koszyka

        return order;
    }

    //todo test it, bo logika operacji na danych jest po stronie JVM a nie bazy danych
    //albo refactor, wyniesc to 'na gore', albo Mockito.mock(copiesRepository) + when(copiesRepositoryMock.findAllByMovie...).thenReturn(movie)
    private MovieCopy getFreeCopyForMovieId(Long movieId) {
        var freeCopy = getAllCopiesByMovieId(movieId).stream()
                .filter(c -> c.getMoviesOrder() == null)
                .findFirst();
        if (freeCopy.isEmpty()){
            throw new CopiesNotFoundException("No free copies of movie movieId=%d available");
        }
        return freeCopy.get();
    }

    private List<MovieCopy> getAllCopiesByMovieId(Long movieId){
       return copiesRepository.findAllByMovie_movieId(movieId);
    }


}
