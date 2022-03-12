package mateuszteam.final_project.config;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.security.AccessRuleAuthorizationManager;
import mateuszteam.final_project.security.UserRepositoryBackedUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig {

    private final UserRepositoryBackedUserDetailsService userRepositoryBackedUserDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AccessRuleAuthorizationManager access) throws Exception {
        http
                .authenticationProvider(authProvider())
                .userDetailsService(userRepositoryBackedUserDetailsService)
                .authorizeHttpRequests((authz) -> authz.anyRequest().access(access))
                .httpBasic(Customizer.withDefaults())
                .csrf().disable();

        return http.build();
    }

    @Bean
    PasswordEncoder pswdEnc() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userRepositoryBackedUserDetailsService);
        authProvider.setPasswordEncoder(pswdEnc());
        return authProvider;
    }
}
