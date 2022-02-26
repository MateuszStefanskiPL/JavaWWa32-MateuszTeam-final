package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.MoviesOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<MoviesOrder, Long> {

}
