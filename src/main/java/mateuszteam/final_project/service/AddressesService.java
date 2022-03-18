package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.AddressDto;
import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class AddressesService {

    private final AddressRepository addressRepository;
    private final UsersMapStructMapper addressMapper;

    public AddressDto findAddressByUserId(final Long userId) {
        var address = addressRepository.findByUser_UserId(userId);

        if (address.isEmpty()) {
            throw new UsernameNotFoundException("No addresses for user " + userId);
        }

        return addressMapper.mapAddressToAddressDto(address.get());
    }

    public AddressDto findAddressByUserEmail(final String email) {
        var address = addressRepository.findByUser_Email(email);
        if (address.isEmpty()) {
            throw new UsernameNotFoundException("No addresses for user " + email);
        }
        return addressMapper.mapAddressToAddressDto(address.get());
    }

    public Address addAddress(final Long userId, final AddressDto addressDto) {
        var addressOptional = addressRepository.findByUser_UserId(userId);
        if (addressOptional.isEmpty()) {
            throw new UsernameNotFoundException("No user  with id  " + userId);
        }
        addressDto.setUserId(userId);
        return addressRepository.save(addressMapper.mapAddressDtoToAddress(addressDto));
    }

    public Address changeAddress(final Long userId, AddressDto newAddressDto) {

        var addressOptional = addressRepository.findByUser_UserId(userId);
        if (addressOptional.isEmpty()) {
            throw new UsernameNotFoundException("No user  with id  " + userId);
        }

        var oldAddress = addressOptional.get();
        oldAddress.setFullName(newAddressDto.getFullName());
        oldAddress.setAddressLine1(newAddressDto.getAddressLine1());
        oldAddress.setAddressLine2(newAddressDto.getAddressLine2());
        oldAddress.setPhone(newAddressDto.getPhone());

        return addressRepository.save(oldAddress);
    }

    public void deleteAddressById(final Long id) {
        var addressOptional = addressRepository.findByUser_UserId(id);
        if (addressOptional.isEmpty()) {
            throw new UsernameNotFoundException("No user  with id  " + id);
        }
        addressRepository.deleteById(id);
    }
}
