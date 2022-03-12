package mateuszteam.final_project.exceptions;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {


    public UserNotFoundException(String email) {
        super(String.format("User with email=%s not found", email));
    }


}
