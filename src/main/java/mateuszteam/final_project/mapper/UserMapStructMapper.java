package mateuszteam.final_project.mapper;

import mateuszteam.final_project.domain.dao.ShippingData;
import mateuszteam.final_project.domain.dao.User;
import mateuszteam.final_project.domain.dto.ShippingDataDto;
import mateuszteam.final_project.domain.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStructMapper {

    User mapFromDtoToDomain(UserDto userDto);

    @InheritInverseConfiguration
    UserDto mapFromDomainToDto(User user);

    ShippingData shippingDataDtoToShippingData(ShippingDataDto shippingDataDto);

    @InheritInverseConfiguration
    ShippingDataDto shippingDataToShippingDataDto(ShippingData shippingData);

}
