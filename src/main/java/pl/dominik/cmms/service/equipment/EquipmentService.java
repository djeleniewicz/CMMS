package pl.dominik.cmms.service.equipment;

import pl.dominik.cmms.entity.equipment.Equipment;

import java.util.List;

public interface EquipmentService {

    Equipment findByName(String name);

    List<Equipment> findAll();
//
//    void saveEquipment(Equipment equipment);
}