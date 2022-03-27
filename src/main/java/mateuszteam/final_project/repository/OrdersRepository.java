package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.OrderStatus;
import mateuszteam.final_project.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<MoviesOrder, Long> {

    Page<MoviesOrder> findByUser_userId(Long id , Pageable pageable);
    List<MoviesOrder> findByUser_Email(String email);

    List<MoviesOrder> findByOrderStatus(OrderStatus status);




}
