package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.security.UserSecuredRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UsersRestController {

    private final UserSecuredRegistrationService usersRegistrationService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public User registerNewUser(@RequestBody UserDto userDto){
        return usersRegistrationService.registerUser(userDto);
    }
/*
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto displayUserData(@PathVariable Long id){
        return usersRegistrationService.findUserDataById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    public UserDto displayUserData(@PathVariable String email){
        return usersRegistrationService.findUserDataByEmail(email);
    }
*/

}
