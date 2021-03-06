package pl.dominik.cmms.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.equipment.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findByName(String name);

    List<Equipment> findAllById(Long id);

    List<Equipment> findAllByStatus(boolean val);

    Equipment findById(Long id);
}