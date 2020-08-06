package pl.dominik.cmms.web.API;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.repository.security.UserRepository;
import pl.dominik.cmms.service.security.UserServiceImpl;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

import static org.apache.tomcat.jni.Time.now;

@Controller
@RestController
@RequestMapping("/api/v1")
public class UserControllerAPI {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public UserControllerAPI(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add-user")
    public User createUser(@Valid @RequestBody User user) {
        user.setCreated(new Timestamp(now()));
        return userService.saveUser(user);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
        User user = userService.findById(userId);
        user.setUsername(userDetails.getUsername());
//        user.setPassword(userDetails.getPassword());
//        user.setLastName(userDetails.getLastName());
//        user.setFirstName(userDetails.getFirstName());
        user.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        final User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

//    @RequestMapping("/add-user")
//    public String addUser(Model model) {
//        User user = new User();
//        model.addAttribute("user", user);
//        return "user/form";
//    }

//    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
//    public String saveUser(@Valid User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "user/form";
//        }
//        System.out.println(result);
//        userService.saveUser(user);
//        return "redirect:/user";
//    }

//    @RequestMapping("/update-user/{id}")
//    public String editUser(@PathVariable int id, Model model) {
//        User user = userRepository.findOne(id);
//        model.addAttribute("user", user);
//        return "/user/formup";
//    }
//
//    @RequestMapping(value = "/update-user", method = RequestMethod.POST)
//    public String editUserPost(@RequestParam Long id, @Valid User user, BindingResult result) {
//        if (result.hasErrors()) {
//            return "user/formup";
//        }
//        user.setId(id);
//        user.setEnabled(1);
//        userService.saveUser(user);
//        return "redirect:/user";
//    }
//
//    @RequestMapping("/delete-user/{id}")
//    public String delUser(@PathVariable int id) {
//        User user = userRepository.findOne(id);
//        user.setEnabled(0);
//        userRepository.save(user);
//        return "redirect:/user";
//    }

//    @RequestMapping("/user-disabled")
//    public String usersEnabled(Model model) {
//        List<User> user = userRepository.findAllByEnabledEquals(0);
//        model.addAttribute("user", user);
//        return "user/user";
//    }

//    @RequestMapping("/enable-user/{id}")
//    public String enableUser(@PathVariable int id) {
//        User user = userRepository.findOne(id);
//        user.setEnabled(1);
//        userRepository.save(user);
//        return "redirect:/user";
//    }
}
