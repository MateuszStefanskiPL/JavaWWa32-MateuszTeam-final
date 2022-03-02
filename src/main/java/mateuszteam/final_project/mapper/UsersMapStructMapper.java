package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.dto.AddressDto;
import mateuszteam.final_project.domain.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapStructMapper {

    User mapFromDtoToDomain(UserDto userDto);

    @InheritInverseConfiguration
    UserDto mapFromDomainToDto(User user);

    Address mapAddressDtoToAddress(AddressDto addressDto);

    @InheritInverseConfiguration
    AddressDto mapAddressToAddressDto(Address address);

}
