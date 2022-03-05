package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Component
public class MoviesStatusChanger {

    private static final LocalDate PREMIER_DATE = LocalDate.now().minusWeeks(2);
    private static final LocalDate NEWEST_DATE = LocalDate.now().minusMonths(3);
    private static final LocalDate STANDARD_DATE = LocalDate.now().minusYears(1);


    private final MoviesRepository moviesRepository;


    //@Scheduled(cron = "@daily")
    @Scheduled(fixedDelay=10000) //co 10tys ms = 10 sec
    public void updateMovieStatus(){
        var allMovies = moviesRepository.findAll();

        for(var movie : allMovies) {
            if(movie.getReleaseDate().isAfter(PREMIER_DATE)) {
                movie.setMovieStatus(MovieStatus.PREMIERE);
            }
            else if (movie.getReleaseDate().isAfter(NEWEST_DATE) && movie.getReleaseDate().isBefore(PREMIER_DATE)){
                movie.setMovieStatus(MovieStatus.NEWEST);
            }
            else if (movie.getReleaseDate().isAfter(STANDARD_DATE) && movie.getReleaseDate().isBefore(NEWEST_DATE)){
                movie.setMovieStatus(MovieStatus.STANDARD);
            }
            else if(movie.getReleaseDate().isBefore(STANDARD_DATE)) {
                movie.setMovieStatus(MovieStatus.CLASSIC);
            }
            log.info("Movie " + movie.getTitle() + " status set to " + movie.getMovieStatus());
        }

        moviesRepository.saveAll(allMovies);
    }

    private void changeStatus(Movie movie, MovieStatus status) {
        movie.setMovieStatus(status);
    }
}
