package pl.dominik.cmms.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.equipment.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}