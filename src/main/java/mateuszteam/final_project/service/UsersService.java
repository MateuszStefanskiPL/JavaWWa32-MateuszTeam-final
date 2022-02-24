package mateuszteam.final_project.service;

import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


}
