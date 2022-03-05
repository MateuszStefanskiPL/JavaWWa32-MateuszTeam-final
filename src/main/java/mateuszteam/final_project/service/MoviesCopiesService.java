package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieCopyDto;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.mapper.MoviesCopiesMapStructMapper;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class MoviesCopiesService {

    private final MoviesCopiesRepository copiesRepository;
    private final MoviesCopiesMapStructMapper copiesMapper;

    public List<MovieCopyDto> findAllCopiesForSingleMovie(Long id) {
        var copy = copiesRepository.findMovieCopiesByMovie_MovieId(id);
        return copy.stream()
                .map(c -> copiesMapper.mapFromDomainToDto(c))
                .collect(Collectors.toList());
    }


}
