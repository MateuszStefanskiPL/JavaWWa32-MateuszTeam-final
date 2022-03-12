package mateuszteam.final_project.repository;

import mateuszteam.final_project.security.AccessRule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRuleRepository extends CrudRepository<AccessRule, String> {
}
