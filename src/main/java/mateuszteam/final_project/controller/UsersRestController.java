package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.security.UserSecuredRegistrationService;
import mateuszteam.final_project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersRestController {

    private final UserSecuredRegistrationService usersRegistrationService;
    private final UsersService usersService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public User registerNewUser(@RequestBody UserDto userDto){
        return usersRegistrationService.registerUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{email}")
    public UserDto displayUserDataByEmail(@PathVariable String email){
        return usersService.findUserDataByEmail(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public UserDto displayUserDataByIdl(@PathVariable Long userId){
        return usersService.findUserDataById(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping ("/newmail/{userId}")
    public User changeUserEmail(@RequestBody String email, @PathVariable Long userId){
        return usersService.updateUserEmail(email,userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/remove/{userId}")
    public void removeUserById(@PathVariable Long userId){
        usersService.deleteUserById(userId);
    }



}
