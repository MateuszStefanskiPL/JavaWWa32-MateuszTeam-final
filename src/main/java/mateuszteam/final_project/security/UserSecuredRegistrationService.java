package mateuszteam.final_project.security;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.UserDto;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.mapper.UsersMapStructMapper;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSecuredRegistrationService {

    private final UsersMapStructMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public User registerUser(UserDto userDto) {
        var user = usersMapper.mapFromDtoToDomain(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserStatus(UserStatus.NEW_USER);
        user.getAuthoritiesList().addAll(userRules());
        return usersRepository.save(user);
    }

    private List<String> userRules(){
        return Arrays.asList("copies:read","addresses:update","addresses:write","addresses:read","addresses:remove",
                "movies:read","orders:read","orders:write","orders:update",
                "ratings:read","/cart:read","/users:read","/users:update","/users:write","/users:remove");
    }

}
