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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapStructMapper ordersMapper;
    private final SessionCartService cartService;
    private final UsersRepository usersRepository;
    private final OrderPriceCalculator priceCalculator;
    private final ApplicationEventPublisher eventPublisher;

    public MoviesOrderDto showOrderByOrderId(Long orderId) {
        if (ordersRepository.findById(orderId).isEmpty()){
            throw new ResourceNotFoundException(orderId);
        }
        return ordersMapper.mapFromDomainToDto(ordersRepository.findById(orderId).get());
    }

    public List<MoviesOrderDto> findAllOrdersByUserId(final Long userId) {
        if (ordersRepository.findByUser_userId(userId, PageRequest.of(3,6)).isEmpty()){
            throw new ResourceNotFoundException("Orders", "id");
        }
        return ordersRepository.findByUser_userId(userId, PageRequest.of(3,6))
                .stream()
                .map(ordersMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    public List<MoviesOrderDto> findAllOrdersByStatus(final OrderStatus status) {
        if (ordersRepository.findByOrderStatus(status).isEmpty()){
            throw new ResourceNotFoundException("Orders", "status");
        }
        List<MoviesOrder> orders = ordersRepository.findByOrderStatus(status);
        return orders.stream()
                .map(ordersMapper::mapFromDomainToDto)
                .collect(Collectors.toList());
    }

    //POST /orders - utworzenie Order po raz pierwszy
    //todo test integracyjny @SpringBootTest (zostawic na koniec)
    public MoviesOrderDto placeOrderFromCart(String userEmail) {
        var cartToOrder = cartService.toOrder();
        var user = getByEmail(userEmail);
        cartToOrder.setUser(user);
        cartToOrder.setPricePerDay(priceCalculator.calculateOrderPricePerDay(cartToOrder));
        cartToOrder = ordersRepository.save(cartToOrder);   //chcemy aby w zwrotce bylo ustawione orderId do odwolania sie
        setOrderNumForCopies(cartToOrder);
        return ordersMapper.mapFromDomainToDto(saveOrder(cartToOrder));
    }

    User getByEmail(String userEmail) {
        var user = usersRepository.findByEmail(userEmail);  //pozniej via Spring Security
        if(user.isEmpty()) {
            throw new ResourceNotFoundException(userEmail, "User");
        }
        return user.get();
    }

    private void setOrderNumForCopies(MoviesOrder order) {
        for(var copy : order.getMovieCopies()) {
            copy.setMoviesOrder(order);
        }
    }

    public MoviesOrderDto acceptOrder(Long orderId) {
        var order = getOrderById(orderId);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        order.setOrderPlacedDate(LocalDateTime.now());
        eventPublisher.publishEvent(OrderPlacedEvent.from(order));
        return ordersMapper.mapFromDomainToDto(saveOrder(order));
    }

    @Scheduled(cron = "@daily")
    void removeNotAcceptedOrders() {
        var ordersToRemove = ordersRepository.findAll().stream()
                .filter(order -> order.getOrderStatus() == null)
                .map(MoviesOrder::getOrderId)
                .collect(Collectors.toList());

        ordersRepository.deleteAllByIdInBatch(ordersToRemove);
    }

    public void deleteOrderByOrderId(final Long orderId) {
        var order = getOrderById(orderId);
        ordersRepository.deleteById(order.getOrderId());
    }

    public MoviesOrder turnBackOrder(Long orderId){
        var order = getOrderById(orderId);
        order.setOrderStatus(OrderStatus.WAY_BACK);
        order.setTotalPrice(priceCalculator.calculateTotalOrderPrice(order));
        eventPublisher.publishEvent(order);
        return saveOrder(order);
    }

    private MoviesOrder getOrderById(Long orderId) {
        var orderOptional = ordersRepository.findById(orderId);
        if(orderOptional.isEmpty()) {
            throw new ResourceNotFoundException(orderId);
        }
        return orderOptional.get();
    }

    private MoviesOrder saveOrder(MoviesOrder order){
        return ordersRepository.save(order);
    }



}
