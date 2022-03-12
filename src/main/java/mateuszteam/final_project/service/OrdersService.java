package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.domain.entities.User;
import mateuszteam.final_project.domain.events.OrderPlacedEvent;
import mateuszteam.final_project.exceptions.ResourceNotFoundException;
import mateuszteam.final_project.mapper.OrdersMapStructMapper;
import mateuszteam.final_project.repository.OrdersRepository;
import mateuszteam.final_project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    private final OrderPriceCalculator priceCalculator;
    private final ApplicationEventPublisher eventPublisher;

    public MoviesOrderDto get(Long orderid) {
        return ordersMapper.mapFromDomainToDto(ordersRepository.findById(orderid).get());
    }

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
    public MoviesOrderDto placeOrderFromCart(String userEmail) {
        var cartToOrder = cartService.toOrder();
        var user = getByEmail(userEmail);
        cartToOrder.setUser(user);
        priceCalculator.setPricePerDayAfterDiscount(cartToOrder);
        cartToOrder = ordersRepository.save(cartToOrder);   //chcemy aby w zwrotce bylo ustawione orderId do odwolania sie
        setOrderForCopies(cartToOrder);
        return ordersMapper.mapFromDomainToDto(ordersRepository.save(cartToOrder));
    }

    private User getByEmail(String userEmail) {
        var user = usersRepository.findByEmail(userEmail);  //pozniej via Spring Security
        if(user.isEmpty()) {
            throw new ResourceNotFoundException(userEmail);
        }
        return user.get();
    }

    private void setOrderForCopies(MoviesOrder order) {
        for(var copy : order.getMovieCopies()) {
            copy.setMoviesOrder(order);
        }
    }

    //tutaj dostajemy sie poprzez PATCH orders/{id}/accept
    //wygeneruj zdarzenie (event) OrderPlaced ktore spowoduje wyslanie maila o zlozonym zamowieniu
    public MoviesOrderDto acceptOrder(Long orderId) {
        var order = getForId(orderId);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setOrderPlacedDate(LocalDateTime.now());
        order = ordersRepository.save(order);
        eventPublisher.publishEvent(OrderPlacedEvent.from(order));
        return ordersMapper.mapFromDomainToDto(order);
    }

    private MoviesOrder getForId(Long orderId) {
        var orderOptional = ordersRepository.findById(orderId);
        if(orderOptional.isEmpty()) {
            throw new ResourceNotFoundException(orderId);
        }
        return orderOptional.get();
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
