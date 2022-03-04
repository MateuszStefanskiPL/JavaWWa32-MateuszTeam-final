package mateuszteam.final_project;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.entities.*;
import mateuszteam.final_project.repository.MoviesRepository;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;


@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DataInitializer implements CommandLineRunner {


    private final UsersRepository usersRepository;
    private final MoviesRepository moviesRepository;


    @Override
    public void run(final String... args) throws Exception {
        initializeData();

    }

    private void initializeData(){

        var user1 = User.builder()
                .email("user1@email.com")
                .password("password1")
                .moneySpent(BigDecimal.valueOf(1050.0D))
                .userStatus(UserStatus.SILVER)
                .build();

        var user2 = User.builder()
                .email("user2@email.com")
                .password("password2")
                .moneySpent(BigDecimal.valueOf(6000.0D))
                .userStatus(UserStatus.GOLD)
                .build();

        var user3 = User.builder()
                .email("user3@email.com")
                .password("password3")
                .moneySpent(BigDecimal.valueOf(16000.0D))
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
                .starring("Amanda Wyss")
               // .starring("Elm Street in provincial Springwood is inhabited by 15-year-old Tina Gray (Amanda Wyss) - a girl who is haunted by nightmares. Their anti-hero is a man in a red and green sweater and an old hat, a scarred face with burns, and armed with a glove with four blades. The dreams become more and more realistic and one night Tina wakes up screaming to discover that her nightgown has been cut, just like in her dream.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(10.0D)
                .build();

        var movie2 = Movie.builder()
                .title("Sing 2")
                .releaseDate(LocalDate.of(2022,1,21))
                .director("Garth Jennings")
                .genre(Genre.ANIMATION)
                .starring("Matthew David McConaughey ,Laura Jeanne Reese Witherspoon")
               // .starring("To perform at the Crystal Theater, Buster Moon and his crew must find and convince the rock legend to return to the stage.")
                .movieStatus(MovieStatus.PREMIERE)
                .numberOfCopies(2)
                .averageScore(0.0D)
                .build();

        var movie3 = Movie.builder()
                .title("Cry-Baby")
                .releaseDate(LocalDate.of(1990,4,6))
                .director("John Waters")
                .genre(Genre.MUSICAL)
                .starring("Johny Depp")
                //.starring("A social outcast(Johnny Depp) whose parents were executed in the electric chair falls in love with a girl from a good home.")
                .movieStatus(MovieStatus.CLASSIC)
                .numberOfCopies(2)
                .averageScore(0.0D)
                .build();

        var copy1 = MovieCopy.builder()
                .movie(movie1)
                .moviesOrder(null)
                .build();
        var copy2 = MovieCopy.builder()
                .movie(movie1)
                .moviesOrder(null)
                .build();

        var copy3 = MovieCopy.builder()
                .movie(movie2)
                .moviesOrder(null)
                .build();

        var copy4 = MovieCopy.builder()
                .movie(movie2)
                .moviesOrder(null)
                .build();

        var copy5 = MovieCopy.builder()
                .movie(movie3)
                .moviesOrder(null)
                .build();

        var copy6 = MovieCopy.builder()
                .movie(movie3)
                .moviesOrder(null)
                .build();

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

        moviesRepository.saveAll(Arrays.asList(movie1,movie2,movie3));



    }





}
