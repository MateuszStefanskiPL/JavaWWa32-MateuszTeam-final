package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dao.User;
import mateuszteam.final_project.domain.dto.UserDto;

@org.mapstruct.Mapper
public interface UserMapStructMapper extends Mapper<User, UserDto> {

    User mapFromDtoToDomain(UserDto userDto);

    UserDto mapFromDomainToDto(User user);
}
