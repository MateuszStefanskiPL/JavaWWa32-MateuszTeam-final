package mateuszteam.final_project.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "year_of_prod")
    private int yearOfProd;

    @Column(name = "director")
    private String director;

    @Column(name = "starring")
    private String starring;

    @Column(name = "copies")
    private int numberOfCopies;

    @OneToMany(mappedBy = "movie")
    private List<MovieCopy> copies;

    @Column(name = "rating")
    private double movieAverageRating;

    @Enumerated
    @Column(name = "movie_status")
    private MovieStatus movieStatus;



}
