package mateuszteam.final_project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "rating_id")
    public long ratingId;

    @NotNull
    @Column(name = "user_id")
    private long userId;

    @NotNull
    @Column(name = "movie_id")
    private long movieId;

    @NotNull
    @Column(name = "rating")
    private double ratingScore;


}
