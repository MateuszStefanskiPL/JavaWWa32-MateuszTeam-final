package mateuszteam.final_project.repository;

import mateuszteam.final_project.domain.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    public Address findByUser_UserId(Long userId);

    public Address findByUser_Email(String email);



}
