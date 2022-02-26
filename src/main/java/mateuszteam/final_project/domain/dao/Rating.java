package mateuszteam.final_project.domain.dao;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
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
    private User user;

    @Column(name = "movie_id")
    private Movie movie;

    @Column(name = "rating")
    private double ratingScore;


}
