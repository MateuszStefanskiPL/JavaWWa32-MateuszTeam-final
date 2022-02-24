package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dao.Movie;
import mateuszteam.final_project.domain.dto.MovieDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapStructMapper extends MapperInterface<Movie, MovieDto>{

    Movie mapFromDtoToDomain(MovieDto movieDto);

    @InheritInverseConfiguration
    MovieDto mapFromDomainToDto(Movie movie);
}
