package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.MovieCopy;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapStructMapper {

    MoviesOrder mapFromDtoToDomain(MoviesOrderDto moviesOrderDto);

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "orderDate", source = "orderPlacedDate")
    @Mapping(target = "dateOfReturn", source = "statusChangeDate")
    @Mapping(target = "price", source = "totalPrice")
    @Mapping(target = "copies", source = "movieCopies")
    MoviesOrderDto mapFromDomainToDto(MoviesOrder moviesOrder);

    default Long map(MovieCopy copy) {
        return copy.getCopyId();
    }
}
