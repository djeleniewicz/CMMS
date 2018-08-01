package pl.dominik.cmms.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.service.security.UserService;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "Nieznajomy") String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/admin")
    public String admin() { return "security/panel"; }

    @RequestMapping("/create-user")
    @ResponseBody
    public void createUser(){
        User user = new User();
        user.setUsername("dominik");
        user.setPassword("dominik");
        userService.saveUser(user);
    }
}