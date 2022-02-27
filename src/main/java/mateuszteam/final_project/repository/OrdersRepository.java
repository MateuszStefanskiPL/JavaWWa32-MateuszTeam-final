package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.MoviesOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<MoviesOrder, Long> {

    Page<MoviesOrder> findByUser_userId(Long id , Pageable pageable);

}
