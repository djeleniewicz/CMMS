package pl.dominik.cmms.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String index(@RequestParam(defaultValue = "Nieznajomy") String name, @AuthenticationPrincipal CurrentUser customUser, Model model) {
        try {
            if (customUser.getUser() != null) {
                User user = customUser.getUser();
                name = user.getFirstName() + " " + user.getLastName();
                model.addAttribute(name);
            }
        } catch (NullPointerException e) {
        }
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

}