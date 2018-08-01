package pl.dominik.cmms.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {


    @RequestMapping("/panel-admin")
    public String admin() {

        return "menu/admin";
    }
}
