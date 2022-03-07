package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.AddressDto;
import mateuszteam.final_project.domain.entities.Address;
import mateuszteam.final_project.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class AddressRestController {

    private final AddressesService addressesService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AddressDto displayAddressByUserId(@PathVariable Long userId){
        return addressesService.findAddressByUserId(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    public AddressDto displayAddressByUserEmail(@PathVariable String email){
        return addressesService.findAddressByUserEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new/{userId}")
    public Address addNewAddress(@PathVariable Long userId, @RequestBody AddressDto addressDto){
        return addressesService.addAddress(userId, addressDto);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update/{userId}")
    public Address updateAddressForUser(@PathVariable Long userId, @RequestBody AddressDto newAddressDto){
        return addressesService.changeAddress(userId, newAddressDto);
    }






}
