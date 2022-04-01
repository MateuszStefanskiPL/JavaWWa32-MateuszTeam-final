package mateuszteam.final_project.domain.events;

import lombok.Data;
import mateuszteam.final_project.domain.entities.MoviesOrder;

@Data
public class OrderReturnedEvent {

    private Long orderId;
    private String userEmail;

    public static OrderReturnedEvent from(MoviesOrder order) {
        var event = new OrderReturnedEvent();
        event.setOrderId(order.getOrderId());
        event.setUserEmail(order.getUser().getEmail());
        return event;
    }
}
