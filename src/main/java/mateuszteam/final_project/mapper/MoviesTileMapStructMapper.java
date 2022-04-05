package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieTileDto;
import mateuszteam.final_project.domain.entities.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviesTileMapStructMapper {

    MovieTileDto mapFromDomainToDto(Movie movie);
}
