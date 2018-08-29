package pl.dominik.cmms.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.orders.Name;

import java.util.List;

@Repository
public interface NameRepository extends JpaRepository<Name, Integer> {

    List<Name> findAllByNameOrName(String s1, String s2);

}