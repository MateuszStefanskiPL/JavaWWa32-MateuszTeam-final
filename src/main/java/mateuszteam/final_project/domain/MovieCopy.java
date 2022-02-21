package mateuszteam.final_project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(name = "movie_id")
    private long movieId;

    @Column(name = "order_id")
    private long orderId;
}
