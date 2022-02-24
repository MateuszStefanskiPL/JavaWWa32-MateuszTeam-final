package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dao.User;
import mateuszteam.final_project.domain.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStructMapper extends MapperInterface<User, UserDto> {

    User mapFromDtoToDomain(UserDto userDto);

    @InheritInverseConfiguration
    UserDto mapFromDomainToDto(User user);
}
