package pl.dominik.cmms.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.equipment.Inspection;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Integer> {
}