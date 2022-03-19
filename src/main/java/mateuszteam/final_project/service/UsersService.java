package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapStructMapper usersMapper;

    public UserDto findUserDataById(final Long id) {
        var user = returnUserIfExists(id);
        return usersMapper.mapFromDomainToDto(user);
    }

    public UserDto findUserDataByEmail(final String email) {
        var user = returnUserIfExists(email);
        return usersMapper.mapFromDomainToDto(user);
    }

    public User updateUserEmail(final String newEmail, final Long userId) {
        var user = returnUserIfExists(userId);
        user.setEmail(newEmail);
        return usersRepository.save(user);
    }

    public void deleteUserById(final Long userId) {
        var user = returnUserIfExists(userId);
        usersRepository.delete(user);
    }

    private User returnUserIfExists(Long userId){
        var user = usersRepository.findByUserId(userId);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("User ", "id");
        }
        return user.get();
    }

    private User returnUserIfExists(String email){
        var user = usersRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new ResourceNotFoundException("User ", "id");
        }
        return user.get();
    }
}
