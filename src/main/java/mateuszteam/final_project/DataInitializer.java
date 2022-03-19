package mateuszteam.final_project;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.entities.*;
import mateuszteam.final_project.repository.AccessRuleRepository;
import mateuszteam.final_project.repository.MoviesCopiesRepository;
import mateuszteam.final_project.repository.MoviesRepository;
import mateuszteam.final_project.repository.UsersRepository;
import mateuszteam.final_project.security.AccessRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DataInitializer implements CommandLineRunner {


    private final UsersRepository usersRepository;
    private final MoviesRepository moviesRepository;
    private final MoviesCopiesRepository copiesRepository;
    private final AccessRuleRepository accessRuleRepository;

    @Override
    public void run(final String... args) throws Exception {
        initializeData();
        //initializeCopies();
        initializeAccessRules();
    }

    private void initializeAccessRules() {

        var acr1 = new AccessRule(HttpMethod.POST, "/copies/**", "copies:write");
        var arc9 = new AccessRule(HttpMethod.GET, "/copies/**", "copies:read");
        var arc10 = new AccessRule(HttpMethod.DELETE, "/copies/**", "copies:remove");

        var arc3 = new AccessRule(HttpMethod.PUT, "/addresses/**", "addresses:update");
        var arc4 = new AccessRule(HttpMethod.POST, "/addresses/**", "addresses:write");
        var arc5 = new AccessRule(HttpMethod.GET, "/addresses/**", "addresses:read");
        var arc6 = new AccessRule(HttpMethod.DELETE, "/addresses/**", "addresses:remove");

        var arc2 = new AccessRule(HttpMethod.POST, "/movies/**", "movies:write");
        var arc7 = new AccessRule(HttpMethod.DELETE, "/movies/**", "movies:remove");
        var arc8 = new AccessRule(HttpMethod.GET, "/movies/**", "movies:read");

        var arc11 = new AccessRule(HttpMethod.GET, "/orders/**", "orders:read");
        var arc12 = new AccessRule(HttpMethod.POST, "/orders/**", "orders:write");
        var arc13 = new AccessRule(HttpMethod.PUT, "/orders/**", "orders:update");
        var arc14 = new AccessRule(HttpMethod.PATCH, "/orders/**", "orders:update");
        var arc15 = new AccessRule(HttpMethod.DELETE, "/orders/**", "orders:remove");

        var arc16 = new AccessRule(HttpMethod.GET, "/ratings/**", "ratings:read");
        var arc17 = new AccessRule(HttpMethod.DELETE, "/ratings/**", "ratings:remove");

        var arc18 = new AccessRule(HttpMethod.DELETE, "/cart/**", "/cart:read");

        var arc19 = new AccessRule(HttpMethod.GET, "/users/**", "/users:read");
        var arc20 = new AccessRule(HttpMethod.POST, "/users/**", "/users:write");
        var arc21 = new AccessRule(HttpMethod.PATCH, "/users/**", "/users:update");
        var arc22 = new AccessRule(HttpMethod.DELETE, "/users/**", "/users:remove");

        accessRuleRepository.saveAll(Arrays.asList(
                acr1,arc2,arc3,arc4,arc5,arc6,arc7,arc8,arc9,arc10,
                arc11,arc12,arc13,arc14,arc15,arc16,arc17,arc18,arc19, arc20,
                arc21,arc22));
    }

    private void initializeData(){

        var admin = User.builder()
                .email("admin@email.com")
                .password("admin")
                .authorities(adminRules())
                .build();

        var user1 = User.builder()
                .email("user1@email.com")
                .password("password1")
                .moneySpent(BigDecimal.valueOf(1050.0D))
                .authorities(userRules())
                .userStatus(UserStatus.SILVER)
                .build();

        var user2 = User.builder()
                .email("user2@email.com")
                .password("password2")
                .moneySpent(BigDecimal.valueOf(6000.0D))
                .authorities(userRules())
                .userStatus(UserStatus.GOLD)
                .build();

        var user3 = User.builder()
                .email("user3@email.com")
                .password("password3")
                .moneySpent(BigDecimal.valueOf(16000.0D))
                .authorities(userRules())
                .userStatus(UserStatus.PLATINUM)
                .build();
        var address1  = Address.builder()
                .fullName("First user")
                .addressLine1("Street 356")
                .addressLine2("05-545 Warsaw")
                .phone("555-999-777")
                .user(user1)
                .build();

        var address2  = Address.builder()
                .fullName("Second user")
                .addressLine1("Avenue 116")
                .addressLine2("15-533 Krakow")
                .phone("111-444-555")
                .user(user2)
                .build();

        var address3  = Address.builder()
                .fullName("Third user")
                .addressLine1("King Plaza 116")
                .addressLine2("46-893 Wroclaw")
                .phone("333-477-565")
                .user(user3)
                .build();

        usersRepository.saveAll(Arrays.asList(user1,user2,user3));

        var movie1 = Movie.builder()
                .title("A Nightmare on Elm Street")
                .releaseDate(LocalDate.of(1984,11,9))
                .director("Wes Craven")
                .genre(Genre.HORROR)
                .starring("Elm Street in provincial Springwood is inhabited by 15-year-old Tina Gray (Amanda Wyss) - a girl who is haunted by nightmares. Their anti-hero is a man in a red and green sweater and an old hat, a scarred face with burns, and armed with a glove with four blades. The dreams become more and more realistic and one night Tina wakes up screaming to discover that her nightgown has been cut, just like in her dream.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(10.0D)
                .build();

        var movie2 = Movie.builder()
                .title("Sing 2")
                .releaseDate(LocalDate.of(2022,1,21))
                .director("Garth Jennings")
                .genre(Genre.ANIMATION)
                .starring("To perform at the Crystal Theater, Buster Moon and his crew must find and convince the rock legend to return to the stage.")
                .movieStatus(MovieStatus.PREMIERE)
                .numberOfCopies(2)
                .averageScore(0.0D)
                .build();

        var movie3 = Movie.builder()
                .title("Cry-Baby")
                .releaseDate(LocalDate.of(1990,4,6))
                .director("John Waters")
                .genre(Genre.MUSICAL)
                .starring("A social outcast(Johnny Depp) whose parents were executed in the electric chair falls in love with a girl from a good home.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(0.0D)
                .build();

        moviesRepository.saveAll(Arrays.asList(movie1,movie2,movie3));

        var rating1 = Rating.builder()
                .text("I like it")
                .movie(movie1)
                .user(user1)
                .score(10.0D)
                .dateOfEvaluation(LocalDateTime.now().minusDays(5))
                .build();

        var rating2 = Rating.builder()
                .text("I like it")
                .movie(movie1)
                .user(user2)
                .score(10.0D)
                .dateOfEvaluation(LocalDateTime.now().minusDays(2))
                .build();

    }

    private void initializeCopies() {
        //przy zapisie copies mamy 'failed to persist detached entity movie'
        var movie1 = moviesRepository.findById(1L).get();
        var copy1 = MovieCopy.builder()
                .movie(movie1)
                .build();
        var copy2 = MovieCopy.builder()
                .movie(movie1)
                .build();

        var movie2 = moviesRepository.findById(2L).get();
        var copy3 = MovieCopy.builder()
                .movie(movie2)
                .build();
        var copy4 = MovieCopy.builder()
                .movie(movie2)
                .build();

        var movie3 = moviesRepository.findById(3L).get();
        var copy5 = MovieCopy.builder()
                .movie(movie3)
                .build();
        var copy6 = MovieCopy.builder()
                .movie(movie3)
                .build();

        copiesRepository.saveAll(Arrays.asList(copy1, copy2, copy3, copy4, copy5, copy6));
    }

    private List<String> adminRules(){
        return accessRuleRepository.findAll().stream().map(AccessRule::getAuthority).collect(Collectors.toList());
    }

    private List<String> userRules(){
        return accessRuleRepository.findAll().stream().map(AccessRule::getAuthority).collect(Collectors.toList());
    }



}
