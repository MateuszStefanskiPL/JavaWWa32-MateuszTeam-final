package mateuszteam.final_project.domain.events;

import lombok.Data;
import mateuszteam.final_project.domain.entities.MoviesOrder;

@Data
public class OrderPlacedEvent {

    private Long orderId;
    private String userEmail;

    public static OrderPlacedEvent from(MoviesOrder order) {
        var event = new OrderPlacedEvent();
        event.setOrderId(order.getOrderId());
        event.setUserEmail(order.getUser().getEmail());
        return event;
    }

}
