package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MovieCopyDto;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.exceptions.CopiesNotFoundException;
import mateuszteam.final_project.exceptions.CopyNotFoundException;
import mateuszteam.final_project.mapper.MoviesCopiesMapStructMapper;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import mateuszteam.final_project.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MoviesCopiesService {

    private final MoviesCopiesRepository copiesRepository;
    private final MoviesCopiesMapStructMapper copiesMapper;
    private final MoviesRepository moviesRepository;

    public List<MovieCopyDto> findAllCopiesForSingleMovie(final Long movieId) {
        var copies = returnAvailableCopiesForMovie(movieId);
        return copies.stream()
                .map(copiesMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    //todo--question-- czy poniższa metoda nie powinna zwracać po prostu MovieCopy?
    public MovieCopyDto create(final Long movieId) {
        var copy = MovieCopy.builder()
                .movie(moviesRepository.findById(movieId).get())
                .build();

        return copiesMapper.mapFromDomainToDto(saveCopy(copy));
    }

    public MovieCopyDto get(Long copyId) {
        var copy = returnCopyIfAvailableById(copyId);
        return copiesMapper.mapFromDomainToDto(copy);
    }

    public void removeCopy(final Long copyId) {
        var copy = returnCopyIfAvailableById(copyId);
        copiesRepository.deleteById(copy.getCopyId());
    }

    List<Long> getIdsFromCopiesList(List<MovieCopy> copies) {
        List<Long> ids = new ArrayList<>();
        for (MovieCopy c : copies) {
            ids.add(c.getCopyId());
        }
        return ids;
    }

    MovieCopy returnCopyIfAvailableById(Long copyId) {
        var copy = copiesRepository.findById(copyId);
        if (copy.isEmpty()) {
            throw new CopyNotFoundException(copyId);
        }
        return copy.get();
    }

    List<MovieCopy> returnAvailableCopiesForMovie(Long movieId){
        var copiesOptional = copiesRepository.findMovieCopiesByMovie_MovieId(movieId);
        if (copiesOptional.isEmpty()) {
            throw new CopiesNotFoundException(movieId);
        }
        return copiesOptional.get();
    }

    private MovieCopy saveCopy(MovieCopy copy){
        return copiesRepository.save(copy);
    }
}
