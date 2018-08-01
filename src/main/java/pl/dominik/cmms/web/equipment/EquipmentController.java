package pl.dominik.cmms.web.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;

import java.util.List;

@Controller
public class EquipmentController {

    private final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @RequestMapping("/equipment")
    public String homeEquipment(Model model) {
        List<Equipment> equipment = equipmentRepository.findAll();
        model.addAttribute("equipment", equipment);
        return "equipment/equipment";
    }

    @RequestMapping("/add-equip")
    public String addEquip(Model model) {
        Equipment equipment = new Equipment();
        model.addAttribute("equipment", equipment);
        return "equipment/form";
    }

    @RequestMapping(value = "/add-equip", method = RequestMethod.POST)
    public String saveEquip(@ModelAttribute Equipment equipment) {
        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }

    @RequestMapping("/update-equip/{id}")
    public String editEquip(@PathVariable int id, Model model) {
        Equipment equipment = equipmentRepository.findOne(id);
        model.addAttribute("equipment", equipment);
        return "/equipment/form";
    }

    @RequestMapping(value = "/update-equip/{id}", method = RequestMethod.POST)
    public String editEquip(@PathVariable int id, @ModelAttribute Equipment equipment) {
        equipment.setId(id);
        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }


    @RequestMapping("/delete-equip/{id}")
    public String delEquip(@PathVariable int id) {
        Equipment equipment = equipmentRepository.findOne(id);
        equipmentRepository.delete(equipment);
        return "redirect:/equipment";
    }

//    @RequestMapping(value = "/confirmDelEquip/{id}", method = RequestMethod.POST)
//    public String delEquipAcc(@PathVariable int id) {
//        Equipment equipment = equipmentRepository.findOne(id);
//        equipmentRepository.delete(equipment);
//        return "redirect:/equipment";
//    }

}
