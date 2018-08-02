package pl.dominik.cmms.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.mechanics.Mechanic;
import pl.dominik.cmms.entity.security.Role;
import pl.dominik.cmms.entity.security.User;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {

    Mechanic findByName(String username);



}