package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.UsersRepository;
import mateuszteam.final_project.service.UsersRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class UsersRestController {

    private final UsersRegistrationService usersRegistrationService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public User registerNewUser(@RequestBody UserDto userDto){
        return usersRegistrationService.registerUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto displayUserData(@PathVariable Long id){
        return usersRegistrationService.findUserData(id);
    }
}
