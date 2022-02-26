package mateuszteam.final_project.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "order_id")
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Set<MovieCopy> movieCopies;

    @Column(name = "order_placed_date")
    private LocalDateTime orderPlacedDate;

    @Enumerated
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "status_change_date")
    private LocalDateTime statusChangeDate;

    @Column(name = "price")
    private BigDecimal price;





}
