package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.MovieCopyDto;
import mateuszteam.final_project.domain.entities.MovieCopy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoviesCopiesMapStructMapper {

    MovieCopy mapFromDtoToDomain(MovieCopyDto movieCopyDto);

    MovieCopyDto mapFromDomainToDto(MovieCopy movieCopy);
}
