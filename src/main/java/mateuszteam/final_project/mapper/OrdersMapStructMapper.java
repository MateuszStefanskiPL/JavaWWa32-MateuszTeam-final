package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersMapStructMapper {

    MoviesOrder mapFromDtoToDomain(MoviesOrderDto moviesOrderDto);

    MoviesOrderDto mapFromDomainToDto(MoviesOrder moviesOrder);
}
