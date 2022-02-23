package mateuszteam.final_project.domain.dao;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "copies")
public class MovieCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "copy_id")
    private long copyId;

    @Column(name = "movie_id")
    private long movieId;

    @Column(name = "order_id")
    private long orderId;
}
