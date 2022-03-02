package mateuszteam.final_project;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.entities.Movie;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.entities.UserStatus;
import mateuszteam.final_project.repository.MoviesRepository;
import mateuszteam.final_project.repository.OrdersRepository;
import mateuszteam.final_project.repository.RatingsRepository;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_={@Autowired})
public class DataInitializer implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final MoviesRepository moviesRepository;
    private final OrdersRepository ordersRepository;
    private final RatingsRepository ratingsRepository;


    @Override
    public void run(final String... args) throws Exception {
        initializeUsers();
        initializeMovies();
        initializeOrders();
        initializeRatings();
        initializeAddresses();

    }

    private void initializeAddresses() {
    }

    private void initializeRatings() {

    }

    private void initializeOrders() {

    }

    private void initializeMovies() {

        var movie1 = Movie.builder()
                .title()
                .releaseDate()
                .director()
                .genre()
                .starring()
                .movieStatus()
                .numberOfCopies()
                .averageScore()
                .build();
    }

    private void initializeUsers(){

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

        List<User> users = Arrays.asList(user1,user2,user3);
        usersRepository.saveAll(users);

    }
}
