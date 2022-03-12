package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieCopyDto;
import mateuszteam.final_project.domain.entities.MovieCopy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MoviesCopiesMapStructMapper {

    MovieCopy mapFromDtoToDomain(MovieCopyDto movieCopyDto);

    @Mapping(target = "movieId", source = "movie.movieId")
    @Mapping(target = "orderId", source = "moviesOrder.orderId")
    MovieCopyDto mapFromDomainToDto(MovieCopy movieCopy);
}
