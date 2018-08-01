package pl.dominik.cmms.service.equipment;

import org.springframework.stereotype.Service;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {


    private final EquipmentRepository equipmentRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment findByName(String name) {
        return equipmentRepository.findByName(name);
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }
}