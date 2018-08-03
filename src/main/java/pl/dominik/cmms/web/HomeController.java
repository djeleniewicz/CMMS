package pl.dominik.cmms.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.service.security.CurrentUser;
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

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

}