package pl.dominik.cmms.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.equipment.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByStatus(String status);

}