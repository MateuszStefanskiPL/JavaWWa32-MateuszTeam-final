package mateuszteam.final_project.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToMany
    @JoinColumn(name = "copy_id")
    private List<MovieCopy> movieCopies;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "return_date")
    private LocalDateTime dateOfReturn;

    @Column(name = "price")
    private BigDecimal price;





}
