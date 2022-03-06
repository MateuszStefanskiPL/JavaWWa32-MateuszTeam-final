package mateuszteam.final_project.service;

import mateuszteam.final_project.domain.entities.MoviesOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService {

    private List<Long> movieIds = new ArrayList<>();

    //cennik bean

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
        //ustalic, czy sa dostepne wolne kopie dla wybranych filmow

        //jesli tak, wyliczamy cene pricePerDay na podstawie filmow oraz dodajemy kopie i zwracamy Order

        //jesli nie, modyfikujemy koszyk i zwracamy uzytkownikowi zamowienie ze zmodyfikowanego koszyka
    }

}
