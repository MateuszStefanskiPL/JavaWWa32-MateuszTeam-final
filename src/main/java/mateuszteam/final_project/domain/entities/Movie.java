package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private long movieId;

    private String title;

    @Enumerated
    private Genre genre;

    private LocalDate releaseDate;

    private String director;

    private String starring;

    @Column(name = "copies")
    private int numberOfCopies;

    @Column(name = "score")
    private double averageScore;

    @Enumerated
    @Column(name = "status")
    private MovieStatus movieStatus;

}
