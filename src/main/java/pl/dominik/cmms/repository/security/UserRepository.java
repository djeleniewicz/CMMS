package pl.dominik.cmms.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.security.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    List<User> findAllByRolesIdAndEnabled(Long i, int j);

    List<User> findAllByRolesId(Long i);

    List<User> findAllByEnabledEquals(int i);

    List<User> findAllById(Long i);

    User findById(Long userId);

}