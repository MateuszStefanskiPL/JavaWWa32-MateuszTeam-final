package mateuszteam.final_project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @NotNull
    @Column(name = "user_id")
    private long userId;

    @NotNull
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @NotNull
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @NotNull
    @Column(name = "return_date")
    private LocalDateTime dateOfReturn;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;





}
