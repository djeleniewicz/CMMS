package pl.dominik.cmms.web.API;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1")
public class EquipmentControllerAPI {

    private final EquipmentRepository equipmentRepository;

    public EquipmentControllerAPI(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/equips")
    public List<Equipment> getAllEquip() {
        List<Equipment> equipments = equipmentRepository.findAll();
        return equipments;
    }


    @GetMapping("/equip/{id}")
    public ResponseEntity<Equipment> getEquipById(@PathVariable(value = "id") Long roleId) {
        Equipment equipment = equipmentRepository.findById(roleId);
        return ResponseEntity.ok(equipment);
    }

    @PostMapping("/add-equip")
    public Equipment createEquip(@Valid @RequestBody Equipment equipment) {
//        equipment.setCreated(new Timestamp(now()));
        return equipmentRepository.save(equipment);
    }

    @PostMapping("/end-equip/{id}")
    public Equipment endEquip(@PathVariable(value = "id") Long equipId, @Valid @RequestBody Equipment equipment) {
//        equipment.setEnd(new Timestamp(now()));
        return equipmentRepository.save(equipment);
    }

    @PostMapping("/update-equip/{id}")
    public ResponseEntity<Equipment> updateEquip(
            @PathVariable(value = "id") Long equipId, @Valid @RequestBody Equipment equipDetails) {
        Equipment equipment = equipmentRepository.findById(equipId);

        equipment.setName(equipDetails.getName());
//        user.setPassword(userDetails.getPassword());
//        user.setLastName(userDetails.getLastName());
//        user.setFirstName(userDetails.getFirstName());
//        equipment.se(new Timestamp(System.currentTimeMillis()));
        final Equipment updatedEquip = equipmentRepository.save(equipment);
        return ResponseEntity.ok(updatedEquip);
    }
}
