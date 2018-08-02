package pl.dominik.cmms.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.mechanics.Mechanic;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.repository.security.MechanicRepository;
import pl.dominik.cmms.repository.security.RoleRepository;
import pl.dominik.cmms.repository.security.UserRepository;
import pl.dominik.cmms.service.security.MechanicService;
import pl.dominik.cmms.service.security.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MechanicController {

    private final MechanicRepository mechanicRepository;
    private final MechanicService mechanicService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public MechanicController(MechanicRepository mechanicRepository, MechanicService mechanicService, RoleRepository roleRepository, UserRepository userRepository, UserService userService) {
        this.mechanicRepository = mechanicRepository;
        this.mechanicService = mechanicService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping("/mechanic")
    public String mechanics(Model model) {
        List<User> mechanic = userRepository.findAllByRolesId(2);
        model.addAttribute("mechanic", mechanic);
        return "mechanics/mechanic";
    }


    @RequestMapping("/add-mech")
    public String addMech(Model model) {
        Mechanic mechanic = new Mechanic();
        model.addAttribute("mechanic", mechanic);
        return "mechanics/form";
    }

    @RequestMapping(value = "/add-mech", method = RequestMethod.POST)
    public String saveMech(@Valid Mechanic mechanic, BindingResult result) {
        if (result.hasErrors()) {
            return "mechanics/form";
        }
        User user = new User();
        user.setUsername(mechanic.getName());
        user.setPassword(mechanic.getPassword());
        userService.saveMechanic(user,mechanic);
        mechanicService.saveMechanic(mechanic);
        return "redirect:/mechanic";
    }

    @RequestMapping("/update-mech/{id}")
    public String editMech(@PathVariable int id, Model model) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        model.addAttribute("mechanic", mechanic);
        model.addAttribute("id", id);
        return "/mechanics/formup";
    }

    @RequestMapping(value = "/update-mech", method = RequestMethod.POST)
    public String editMech(@RequestParam int id, @Valid Mechanic mechanic, BindingResult result) {
        if (result.hasErrors()) {
            return "mechanics/formup";
        }
        User user = userRepository.findOne(id);
        user.setUsername(mechanic.getName());
        user.setPassword(mechanic.getPassword());
        userService.saveMechanic(user,mechanic);
        mechanic.setId(id);
        mechanic.setEnabled(1);
        mechanicRepository.save(mechanic);
        return "redirect:/mechanic";
    }


    @RequestMapping("/delete-mech/{id}")
    public String delMech(@PathVariable int id) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        mechanic.setEnabled(0);
        mechanicRepository.save(mechanic);
//        mechanicRepository.delete(mechanic);
        return "redirect:/mechanic";
    }

    @RequestMapping("/role")
    @ResponseBody
    public String role() {
        return "acc";
    }
}
