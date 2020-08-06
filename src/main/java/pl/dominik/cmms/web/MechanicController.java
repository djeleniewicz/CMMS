package pl.dominik.cmms.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.repository.security.RoleRepository;
import pl.dominik.cmms.repository.security.UserRepository;
import pl.dominik.cmms.service.security.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MechanicController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public MechanicController(RoleRepository roleRepository, UserRepository userRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/mechanic")
    public String mechanics(Model model) {
        List<User> mechanic = userRepository.findAllByEnabledEquals(1);
        model.addAttribute("mechanic", mechanic);
        return "mechanics/mechanic";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/add-mech")
    public String addMech(Model model) {
        User user = new User();
        model.addAttribute("mechanic", user);
        return "mechanics/form";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add-mech", method = RequestMethod.POST)
    public String saveMech(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "mechanics/form";
        }
        userService.saveMechanic(user);
        return "redirect:/mechanic";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/update-mech/{id}")
    public String editMech(@PathVariable int id, Model model) {
        User mechanic = userRepository.findOne(id);
        model.addAttribute("mechanic", mechanic);
        model.addAttribute("id", id);
        return "/mechanics/formup";
    }

//    @Secured("ROLE_ADMIN")
//    @RequestMapping(value = "/update-mech", method = RequestMethod.POST)
//    public String editMech(@RequestParam int id, @Valid User mechanic, BindingResult result) {
//        if (result.hasErrors()) {
//            return "mechanics/formup";
//        }
//        mechanic.setId(id);
//        userService.saveMechanic(mechanic);
//        return "redirect:/mechanic";
//    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete-mech/{id}")
    public String delMech(@PathVariable int id) {
        User user = userRepository.findOne(id);
        user.setEnabled(0);
        userService.saveMechanic(user);
        return "redirect:/mechanic";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/mechanic-disabled")
    public String disabledMechanic(Model model) {
        List<User> mechanic = userRepository.findAllByEnabledEquals(0);
        model.addAttribute("mechanic", mechanic);
        return "mechanics/mechanic";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/enable-mech/{id}")
    public String enableMechanic(@PathVariable int id) {
        User user = userRepository.findOne(id);
        user.setEnabled(0);
        userService.saveMechanic(user);
        return "redirect:/mechanic";
    }

}
