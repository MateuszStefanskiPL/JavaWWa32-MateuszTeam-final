package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class UsersRegistrationService {

    private final UsersRepository usersRepository;
    private final UsersMapStructMapper usersMapper;


    public User registerUser(final UserDto userDto) {
        var user = usersMapper.mapFromDtoToDomain(userDto);
        return usersRepository.save(user);
    }
}
