package pl.dominik.cmms.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.repository.security.UserRepository;
import pl.dominik.cmms.service.security.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String users(Model model){
        List<User> user = userRepository.findAll();
        model.addAttribute("user", user);
        return "user/user";
    }


    @RequestMapping("/add-user")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/form";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @RequestMapping("/update-user/{id}")
    public String editEquip(@PathVariable int id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "/user/form";
    }

    @RequestMapping(value = "/update-user/{id}", method = RequestMethod.POST)
    public String editEquip(@PathVariable int id, @ModelAttribute User user) {
        user.setId(id);
        userRepository.save(user);
        return "redirect:/user";
    }


    @RequestMapping("/delete-user/{id}")
    public String delEquip(@PathVariable int id) {
        User user = userRepository.findOne(id);
        userRepository.delete(user);
        return "redirect:/user";
    }
}
