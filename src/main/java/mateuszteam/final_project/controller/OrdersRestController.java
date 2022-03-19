package mateuszteam.final_project.controller;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrdersRestController {

    private final OrdersService ordersService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    public List<MoviesOrderDto> displayAllOrdersForUser(@PathVariable Long id) {
        return ordersService.findAllOrdersByUserId(id);
    }

    @GetMapping("/order/{id}")
    MoviesOrderDto get(@PathVariable Long id) {
        return ordersService.showOrderByOrderId(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{status}")
    public List<MoviesOrderDto> displayOrdersByStatus(@PathVariable OrderStatus status) {
        return ordersService.findAllOrdersByStatus(status);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping    // /orders?email=xxx
    public MoviesOrderDto putOrderToCart(@RequestParam String email) {
        return ordersService.placeOrderFromCart(email);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{orderId}/accept")
    public MoviesOrderDto acceptOrderByOrderID(@PathVariable Long orderId) {
        return ordersService.acceptOrder(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove/{orderId}")
    public void removeOrderByOrderId(@PathVariable Long orderId) {
        ordersService.deleteOrderByOrderId(orderId);
    }


}
