package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders")
public class MoviesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id")
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "moviesOrder", cascade = CascadeType.PERSIST)
    private Set<MovieCopy> movieCopies;

    @Column(name = "order_placed_date")
    private LocalDateTime orderPlacedDate;

    @Enumerated
    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "status_change_date")
    private LocalDateTime statusChangeDate;

    private BigDecimal price;





}
