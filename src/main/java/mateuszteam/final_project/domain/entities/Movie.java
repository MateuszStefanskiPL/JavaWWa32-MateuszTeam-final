package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;

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

    private String title;

    @Enumerated
    private Genre genre;

    @Column(name = "year_of_prod")
    private int yearOfProd;

    @Column(name = "director")
    private String director;

    @Column(name = "starring")
    private String starring;

    @Column(name = "copies")
    private int numberOfCopies;

//    @OneToMany(mappedBy = "movie")
//    private List<MovieCopy> copies;

    @Column(name = "rating")
    private double averageScore;

    @Enumerated
    @Column(name = "movie_status")
    private MovieStatus movieStatus;



}
