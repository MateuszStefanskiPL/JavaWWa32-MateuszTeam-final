package mateuszteam.final_project.domain.dao;

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

    @Column(name= "title")
    private String movieTitle;

    @Column(name = "year_of_prod")
    private int yearOfProd;

    @Column(name = "director")
    private String director;

    @Column(name = "starring")
    private String starring;

    @Column(name = "copies")
    private int numberOfCopies;

    @Column(name = "rating")
    private double movieAverageRating;

    @Column(name = "movie_status")
    private MovieStatus movieStatus;



}
