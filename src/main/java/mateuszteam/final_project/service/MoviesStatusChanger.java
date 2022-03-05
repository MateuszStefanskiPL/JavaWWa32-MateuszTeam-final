package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor_={@Autowired})
public class MoviesStatusChanger {

    private final MoviesRepository moviesRepository;

    public void updateMovieStatus(){

    }
}
