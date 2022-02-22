package mateuszteam.final_project.domain.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "user_id")
    private long userId;

    @Column(name = "movie_id")
    private long movieId;

    @Column(name = "rating")
    private double ratingScore;


}
