package pl.dominik.cmms.web.old;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.equipment.Equipment;
import pl.dominik.cmms.entity.equipment.Location;
import pl.dominik.cmms.entity.equipment.Status;
import pl.dominik.cmms.repository.equipment.EquipmentRepository;
import pl.dominik.cmms.repository.equipment.LocationRepository;
import pl.dominik.cmms.repository.equipment.StatusRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EquipmentController {

    private final EquipmentRepository equipmentRepository;
    private final StatusRepository statusRepository;
    private final LocationRepository locationRepository;


    public EquipmentController(EquipmentRepository equipmentRepository, StatusRepository statusRepository, LocationRepository locationRepository) {
        this.equipmentRepository = equipmentRepository;
        this.statusRepository = statusRepository;
        this.locationRepository = locationRepository;
    }

    @ModelAttribute("status")
    public List<Status> getStatus() {
        return statusRepository.findAll();
    }

    @ModelAttribute("location")
    public List<Location> getLocation() {
        return locationRepository.findAll();
    }

//    @ModelAttribute("inspection")
//    public List<Inspection> getInsp() {
//        return inspectionRepository.findAll();
//    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/equipment")
    public String homeEquipment(Model model) {
        List<Equipment> equipment = equipmentRepository.findAllByStatus(true);
        model.addAttribute("equipment", equipment);
        return "equipment/equipment";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/equipment-off")
    public String equipmentOff(Model model) {
        List<Equipment> list = equipmentRepository.findAllByStatus(false);
        model.addAttribute("equipment", list);
        return "order/equipment";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping("/add-equip")
    public String addEquip(Model model) {
        Equipment equipment = new Equipment();
        model.addAttribute("equipment", equipment);
        return "equipment/form";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping(value = "/add-equip", method = RequestMethod.POST)
    public String saveEquip(@Valid Equipment equipment, BindingResult result) {
        if (result.hasErrors()) {
            return "equipment/form";
        }
        equipment.setStatus(true);
        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping("/update-equip")
    public String editEquip(@RequestParam int id, Model model) {
        Equipment equipment = equipmentRepository.findOne(id);
        model.addAttribute("equipment", equipment);
        return "/equipment/formup";
    }

    @Secured({"ROLE_MECH", "ROLE_ADMIN"})
    @RequestMapping(value = "/update-equip", method = RequestMethod.POST)
    public String editEquip(@RequestParam int id, @Valid Equipment equipment, BindingResult result) {
        if (result.hasErrors()) {
            return "equipment/formup";
        }
//        equipment.setId(id);
        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete-equip/{id}")
    public String delEquip(@PathVariable int id) {
        Equipment equipment = equipmentRepository.findOne(id);
        equipment.setStatus(true);
        equipmentRepository.save(equipment);
        return "redirect:/equipment";
    }

}
