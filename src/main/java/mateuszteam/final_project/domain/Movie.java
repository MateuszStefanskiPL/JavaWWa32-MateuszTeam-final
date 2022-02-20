package mateuszteam.final_project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "movie_id")
    private long movieId;


    @NotEmpty
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name= "title")
    private String movieTitle;


    @NotNull
    @Column(name = "year_of_prod")
    private int yearOfProd;

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "director")
    private String director;

    @NotEmpty
    @NotNull
    @Column(name = "starring")
    private String starring;

    @NotNull
    @Column(name = "copies")
    private int numberOfCopies;

    @NotNull
    @Column(name = "rating")
    private double movieAverageRating;

    @NotNull
    @Column(name = "status")
    private MovieStatus movieStatus;



}
