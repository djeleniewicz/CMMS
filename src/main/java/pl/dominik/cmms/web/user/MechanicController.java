package pl.dominik.cmms.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dominik.cmms.entity.mechanics.Mechanic;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.repository.security.MechanicRepository;
import pl.dominik.cmms.repository.security.UserRepository;
import pl.dominik.cmms.service.security.MechanicService;
import pl.dominik.cmms.service.security.UserService;

import java.util.List;

@Controller
public class MechanicController {

    private final MechanicRepository mechanicRepository;
    private final MechanicService mechanicService;

    public MechanicController(MechanicRepository mechanicRepository, MechanicService mechanicService) {
        this.mechanicRepository = mechanicRepository;
        this.mechanicService = mechanicService;
    }

    @RequestMapping("/mechanic")
    public String mechanics(Model model){
        List<Mechanic> mechanics = mechanicRepository.findAll();
        model.addAttribute("mechanic", mechanics);
        return "mechanics/mechanic";
    }


    @RequestMapping("/add-mech")
    public String addMech(Model model) {
        Mechanic mechanic = new Mechanic();
        model.addAttribute("mechanic", mechanic);
        return "mechanics/form";
    }

    @RequestMapping(value = "/add-mech", method = RequestMethod.POST)
    public String saveMech(@ModelAttribute Mechanic mechanic) {
        mechanicService.saveMechanic(mechanic);
        return "redirect:/mechanic";
    }

    @RequestMapping("/update-mech/{id}")
    public String editMech(@PathVariable int id, Model model) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        model.addAttribute("mechanic", mechanic);
        model.addAttribute("id",id);
        return "/mechanics/form";
    }

    @RequestMapping(value = "/update-mech/{id}", method = RequestMethod.POST)
    public String editMech(@PathVariable int id, @ModelAttribute Mechanic mechanic) {
        System.out.println(id);
        mechanic.setId(id);
        mechanicRepository.save(mechanic);
        return "redirect:/mechanic";
    }


    @RequestMapping("/delete-mech/{id}")
    public String delMech(@PathVariable int id) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        mechanicRepository.delete(mechanic);
        return "redirect:/mechanic";
    }
}
