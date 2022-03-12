package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class UsersRegistrationService {

    private final UsersRepository usersRepository;
    private final UsersMapStructMapper usersMapper;


    public UserDto findUserDataById(final Long id) {
        var user = usersRepository.findByUserId(id).orElseThrow(NoSuchElementException::new);
        return usersMapper.mapFromDomainToDto(user);
    }

    public UserDto findUserDataByEmail(final String email) {
        var user = usersRepository.findByEmail(email).orElseThrow(NoSuchElementException::new);
        return usersMapper.mapFromDomainToDto(user);
    }
}
