package mateuszteam.final_project.domain.dao;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "user_id")
    private long userId;


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
