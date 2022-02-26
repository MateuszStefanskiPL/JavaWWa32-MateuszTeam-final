package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.dto.MovieDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapStructMapper{


    Movie mapFromDtoToDomain(MovieDto movieDto);

    @InheritInverseConfiguration
    MovieDto mapFromDomainToDto(Movie movie);


}
