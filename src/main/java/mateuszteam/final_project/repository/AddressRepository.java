package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> findByUser_UserId(Long userId);

    Optional<Address> findByUser_Email(String email);

}
