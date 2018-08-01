package pl.dominik.cmms.web.equipment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.equipment.Inspection;
import pl.dominik.cmms.entity.equipment.Location;
import pl.dominik.cmms.entity.equipment.Status;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;
import pl.dominik.cmms.repository.equipment.InspectionRepository;
import pl.dominik.cmms.repository.equipment.LocationRepository;
import pl.dominik.cmms.repository.equipment.StatusRepository;

import java.util.List;

@Controller
public class EquipmentController {

    private final EquipmentRepository equipmentRepository;
    private final StatusRepository statusRepository;
    private final LocationRepository locationRepository;
    private final InspectionRepository inspectionRepository;


    public EquipmentController(EquipmentRepository equipmentRepository, StatusRepository statusRepository, LocationRepository locationRepository, InspectionRepository inspectionRepository) {
        this.equipmentRepository = equipmentRepository;
        this.statusRepository = statusRepository;
        this.locationRepository = locationRepository;
        this.inspectionRepository = inspectionRepository;
    }

    @ModelAttribute("status")
    public List<Status> getStatus() {
        return statusRepository.findAll();
    }

    @ModelAttribute("location")
    public List<Location> getLocation() {
        return locationRepository.findAll();
    }

    @ModelAttribute("inspection")
    public List<Inspection> getInsp() {
        return inspectionRepository.findAll();
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
