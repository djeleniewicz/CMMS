package pl.dominik.cmms.web.API;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.dominik.cmms.entity.security.Role;
import pl.dominik.cmms.repository.security.RoleRepository;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

import static org.apache.tomcat.jni.Time.now;

@Controller
@RestController
@RequestMapping("/api/v1")
public class RoleControllerAPI {

    private final RoleRepository roleRepository;

    public RoleControllerAPI(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId) {
        Role role = roleRepository.findById(roleId);
        return ResponseEntity.ok(role);
    }

    @PostMapping("/add-role")
    public Role createRole(@Valid @RequestBody Role role) {
        role.setCreated(new Timestamp(now()));
//        role.setCreatedBy();
        return roleRepository.save(role);
    }

}
