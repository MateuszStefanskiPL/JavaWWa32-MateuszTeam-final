package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.entities.OrderStatus;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MoviesOrderDto {

    @NotNull
    @Setter(AccessLevel.NONE)
    private long orderId;

    @NotNull
    private long userId;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private LocalDateTime dateOfReturn;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

}
