package mateuszteam.final_project.domain.dto;

import lombok.*;
import mateuszteam.final_project.domain.entities.OrderStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Data
@Getter
@Setter
public class MoviesOrderDto {

    @NotNull
    @Setter(AccessLevel.NONE)
    private long orderId;

    @NotNull
    private long userId;

    @DateTimeFormat(fallbackPatterns = {"yyyy-MM-dd'T'HH:mm"})
    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private LocalDateTime dateOfReturn;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    private BigDecimal pricePerDay;

    private Set<Long> copies = new HashSet<>();

}
