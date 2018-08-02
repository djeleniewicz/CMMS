package pl.dominik.cmms.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.security.User;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    User findByUsername(String username);
}