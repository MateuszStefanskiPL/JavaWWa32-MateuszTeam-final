package mateuszteam.final_project.domain;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;

    private String movieTitle;
    private String yearOfProd;
    private String director;
    private String starring;
    private int numberOfCopies;
    private double movieAverageRating;
    private MovieStatus movieStatus;



}
