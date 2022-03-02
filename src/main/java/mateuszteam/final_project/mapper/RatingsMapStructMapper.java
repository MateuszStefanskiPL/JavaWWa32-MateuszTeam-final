package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieDto;
import mateuszteam.final_project.domain.dto.RatingDto;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingsMapStructMapper {

    Rating mapFromDtoToDomain(RatingDto ratingDto);

    RatingDto mapFromDomainToDto(Rating rating);
}
