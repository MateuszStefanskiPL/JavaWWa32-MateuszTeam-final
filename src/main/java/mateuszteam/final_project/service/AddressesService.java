package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.AddressDto;
import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class AddressesService {

    private final AddressRepository addressRepository;
    private final UsersMapStructMapper addressMapper;

    public AddressDto findAddressByUserId(final Long userId) {
        var address = addressRepository.findByUser_UserId(userId);
        //todo handle user not found
        return addressMapper.mapAddressToAddressDto(address);
    }

    public AddressDto findAddressByUserEmail(final String email){
        var address = addressRepository.findByUser_Email(email);
        //todo handle user not found
        return addressMapper.mapAddressToAddressDto(address);
    }

    public Address addAddress(final Long userId, final AddressDto addressDto) {
        addressDto.setUserId(userId);
        //todo handle user not found (bo nie mozemy dodac adresu do nieistniejacego uzytkownika)
        return addressRepository.save(addressMapper.mapAddressDtoToAddress(addressDto));
    }

    public Address changeAddress(final Long userId, AddressDto newAddressDto) {
        var addressDto = addressMapper.mapAddressToAddressDto(addressRepository.findByUser_UserId(userId));
        //todo handle user not found
        addressDto = newAddressDto;
        return addressRepository.save(addressMapper.mapAddressDtoToAddress(addressDto));
    }


}
