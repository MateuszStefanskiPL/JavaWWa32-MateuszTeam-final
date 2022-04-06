package mateuszteam.final_project.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.exceptions.CopiesNotFoundException;
import mateuszteam.final_project.exceptions.DuplicateOrderException;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
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

        //caly koszyk mozna konwertowac na zamowienie
        if(freeCopiesForAllMoviesAvailable(orderedCopies)) {
            var order = new MoviesOrder();
            order.setMovieCopies(orderedCopies.stream()
                    .map(CopySearchResult::getCopy)
                    .collect(Collectors.toSet()));
            return order;
        }

        //nie wszystkie zamawiane filmy posiadaja wolne kopie, modyfikacja koszyka
        var moviesWihoutFreeCopies = orderedCopies.stream()
                .filter(result -> result.getCopy() == null)
                .map(CopySearchResult::getMovieId)
                .collect(Collectors.toList());
        //modyfikuj koszyk usuwajac z niego filmy dla ktorych nie ma wolnych kopii
        movieIds.removeAll(moviesWihoutFreeCopies);

        throw new CopiesNotFoundException(moviesWihoutFreeCopies);
    }

    private boolean freeCopiesForAllMoviesAvailable(Set<CopySearchResult> searchResults) {
        return searchResults.stream()
                .noneMatch(result -> result.getCopy() == null);
    }

    CopySearchResult getFreeCopyForMovieId(Long movieId) {
        var freeCopy = returnCopyIfAvailableByMovieId(movieId);
        if (freeCopy.isEmpty()){
            return new CopySearchResult(movieId, null);
        }
        return new CopySearchResult(movieId, freeCopy.get());
    }

    private Optional<MovieCopy> returnCopyIfAvailableByMovieId(Long movieId){
        var copies = getAllCopiesByMovieId(movieId);
        return copies.stream()
                .filter(c -> c.getMoviesOrder() == null)
                .findFirst();
    }

    private List<MovieCopy> getAllCopiesByMovieId(Long movieId){
        var copies = copiesRepository.findAllByMovie_movieId(movieId);
        if (copies.isEmpty()){
            throw new CopiesNotFoundException(movieId);
        }
       return copies;
    }

    //klasa reprezentuje wynik poszukiwania wolnej kopii dla danego filmu
    //jesli copyId != null to wolna kopia dla tego filmu istnieje
    @Getter
    @RequiredArgsConstructor
    static class CopySearchResult {
        private final Long movieId;
        private final MovieCopy copy;
    }


}
