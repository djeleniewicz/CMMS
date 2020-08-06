package pl.dominik.cmms.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.requests.Accident;

@Repository
public interface AccidentRepository extends JpaRepository<Accident, Long> {
    Accident findById(Long id);
}
