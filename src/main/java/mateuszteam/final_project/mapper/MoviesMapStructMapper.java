package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.MovieTileDto;
import mateuszteam.final_project.domain.entities.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviesMapStructMapper {


    Movie mapFromDtoToDomain(MovieDto movieDto);

    MovieDto mapFromDomainToDto(Movie movie);

    MovieTileDto mapFromDomainToTileDto(Movie movie);
}
