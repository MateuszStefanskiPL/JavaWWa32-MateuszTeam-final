package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {

    public Rating(Movie movie, double score) {
        this.movie = movie;
        this.score = score;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    public long ratingId;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;

    private double score;

    private String text;

    private LocalDateTime dateOfEvaluation;

}
