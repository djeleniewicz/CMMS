package pl.dominik.cmms.repository.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.security.Role;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findByName(String name);

    @Query("delete from Equipment b where b.id =?1")
    Equipment deleteById(int id);
}