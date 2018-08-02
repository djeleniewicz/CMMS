package pl.dominik.cmms.web.user;

import org.springframework.security.access.annotation.Secured;
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

    @Secured("ROLE_ADMIN")
    @RequestMapping("/mechanic")
    public String mechanics(Model model) {
        List<User> mechanic = userRepository.findAllByRolesId(2);
        model.addAttribute("mechanic", mechanic);
        return "mechanics/mechanic";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/add-mech")
    public String addMech(Model model) {
        Mechanic mechanic = new Mechanic();
        model.addAttribute("mechanic", mechanic);
        return "mechanics/form";
    }

    @Secured("ROLE_ADMIN")
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

    @Secured("ROLE_ADMIN")
    @RequestMapping("/update-mech/{id}")
    public String editMech(@PathVariable int id, Model model) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        model.addAttribute("mechanic", mechanic);
        model.addAttribute("id", id);
        return "/mechanics/formup";
    }

    @Secured("ROLE_ADMIN")
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
        mechanicService.saveMechanic(mechanic);
        mechanicRepository.save(mechanic);
        return "redirect:/mechanic";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete-mech/{id}")
    public String delMech(@PathVariable int id) {
        Mechanic mechanic = mechanicRepository.findOne(id);
        mechanic.setEnabled(0);
        User user = userRepository.findOne(id);
        user.setEnabled(0);
        userService.saveUser(user);
        mechanicService.saveMechanic(mechanic);
//        mechanicRepository.delete(mechanic);
        return "redirect:/mechanic";
    }


}
