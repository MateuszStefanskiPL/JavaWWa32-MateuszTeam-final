package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieCopyDto;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.mapper.MoviesCopiesMapStructMapper;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class MoviesCopiesService {

    private final MoviesCopiesRepository copiesRepository;
    private final MoviesCopiesMapStructMapper copiesMapper;
    private final MoviesRepository moviesRepository;

    public List<MovieCopyDto> findAllCopiesForSingleMovie(final Long id) {
        var copy = copiesRepository.findMovieCopiesByMovie_MovieId(id);
        return copy.stream()
                .map(c -> copiesMapper.mapFromDomainToDto(c))
                .collect(Collectors.toList());
    }

    public MovieCopyDto create(final Long movieId) {
        var copy = new MovieCopy();
        copy.setMovie(moviesRepository.findById(movieId).get());
        copy = copiesRepository.save(copy);
        return copiesMapper.mapFromDomainToDto(copy);
    }

    public MovieCopyDto get(Long copyId) {
        return copiesMapper.mapFromDomainToDto(copiesRepository.findById(copyId).get());
    }


}
