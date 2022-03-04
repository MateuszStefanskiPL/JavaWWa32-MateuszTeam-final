package mateuszteam.final_project.service;

import lombok.RequiredArgsConstructor;
import mateuszteam.final_project.domain.dto.MoviesOrderDto;
import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.mapper.OrdersMapStructMapper;
import mateuszteam.final_project.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_={@Autowired})
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapStructMapper ordersMapper;

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
}
