package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.MoviesOrder;
import mateuszteam.final_project.domain.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrdersRepository extends CrudRepository<MoviesOrder, Long> {

    //todo napisać metodę ktora pobierze Usera z Order


}
