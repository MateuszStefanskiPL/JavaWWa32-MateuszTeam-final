package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieStatus;
import mateuszteam.final_project.mapper.MoviesMapStructMapper;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class MoviesStatusChangerService {

    private static final LocalDate PREMIER_DATE = LocalDate.now().minusWeeks(2);
    private static final LocalDate NEWEST_DATE = LocalDate.now().minusMonths(3);
    private static final LocalDate STANDARD_DATE = LocalDate.now().minusYears(1);

    private final MoviesRepository moviesRepository;

    @Scheduled(cron = "@daily")
    public void saveUpdatedMovies(){
        List<Movie> changedMovies = new ArrayList<>(updateMoviesStatus());
        moviesRepository.saveAll(changedMovies);
    }

    private List<Movie> updateMoviesStatus(){
        return updateMoviesStatus(moviesRepository.findAll());
    }

    //todo: test it -> zasilic lista 4 filmow i sprawdzic czy na wyjsciu zmienily sie statusy
    List<Movie> updateMoviesStatus(List<Movie> movies) {
        movies.forEach(this::updateMovieStatus);
        return movies;
    }

    private Movie updateMovieStatus(Movie movie){
        if(movie.getReleaseDate().isAfter(PREMIER_DATE)) {
            changeStatus(movie,MovieStatus.PREMIERE);
        }
        else if (movie.getReleaseDate().isAfter(NEWEST_DATE) && movie.getReleaseDate().isBefore(PREMIER_DATE)){
            changeStatus(movie,MovieStatus.NEWEST);
        }
        else if (movie.getReleaseDate().isAfter(STANDARD_DATE) && movie.getReleaseDate().isBefore(NEWEST_DATE)){
            changeStatus(movie,MovieStatus.STANDARD);
        }
        else if(movie.getReleaseDate().isBefore(STANDARD_DATE)) {
            changeStatus(movie,MovieStatus.CLASSIC);
        }
        log.info("Movie " + movie.getTitle() + " status set to " + movie.getMovieStatus());
        return movie;
    }

    private void changeStatus(Movie movie, MovieStatus status) {
        movie.setMovieStatus(status);
    }
}
