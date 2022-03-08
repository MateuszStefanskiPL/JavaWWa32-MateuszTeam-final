package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.mapper.OrdersMapStructMapper;
import mateuszteam.final_project.repository.OrdersRepository;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapStructMapper ordersMapper;
    private final SessionCartService cartService;
    private final UsersRepository usersRepository;


    public List<MoviesOrderDto> findAllOrdersByUserId(final Long id) {
        return ordersRepository.findByUser_userId(id, PageRequest.of(3,6))
                .stream()
                .map(o -> ordersMapper.mapFromDomainToDto(o))
                .collect(Collectors.toList());
    }

    public MoviesOrder addNewOrder(MoviesOrderDto moviesOrderDto){
        var order = ordersMapper.mapFromDtoToDomain(moviesOrderDto);
        return order;
    }

    public List<MoviesOrderDto> findAllOrdersByStatus(final OrderStatus status) {

        List<MoviesOrder> orders = ordersRepository.findByOrderStatus(status);
        return orders.stream()
                .map(o -> ordersMapper.mapFromDomainToDto(o))
                .collect(Collectors.toList());
    }

    //POST /orders - utworzenie Order po raz pierwszy
    public MoviesOrder placeOrderInCart(String userEmail) {
        var cartToOrder = cartService.toOrder();
        var user = usersRepository.findByEmail(userEmail);
        cartToOrder.setUser(user.get());
        return cartToOrder;
    }

    //tutaj dostajemy sie poprzez PATCH orders/{id}/accept
    //wygeneruj zdarzenie (event) OrderPlaced ktore spowoduje wyslanie maila o zlozonym zamowieniu
    public MoviesOrder acceptOrder(Long orderId) {
        var order = ordersRepository.findById(orderId).get();
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setOrderPlacedDate(LocalDateTime.now());
        return order;
    }

    //usuwanie zamowien ktore nie zostaly zaakceptowane - raz na dobe
    @Scheduled(cron = "@daily")
    void removeNotAcceptedOrders() {
        var ordersToRemove = ordersRepository.findAll().stream()
                .filter(order -> order.getOrderStatus() == null)
                .map(MoviesOrder::getOrderId)
                .collect(Collectors.toList());

        ordersRepository.deleteAllByIdInBatch(ordersToRemove);
    }
}
