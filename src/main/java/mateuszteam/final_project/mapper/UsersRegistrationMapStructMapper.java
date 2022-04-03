package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dto.UserRegistrationDto;
import mateuszteam.final_project.domain.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersRegistrationMapStructMapper {

    User mapFromDtoToDomain(UserRegistrationDto userDto);

    @InheritInverseConfiguration
    UserRegistrationDto mapFromDomainToDto(User user);
}
