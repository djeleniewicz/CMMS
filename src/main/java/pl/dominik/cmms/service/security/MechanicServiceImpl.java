package pl.dominik.cmms.service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dominik.cmms.entity.mechanics.Mechanic;
import pl.dominik.cmms.entity.security.Role;
import pl.dominik.cmms.entity.security.User;
import pl.dominik.cmms.repository.security.MechanicRepository;
import pl.dominik.cmms.repository.security.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MechanicServiceImpl(MechanicRepository mechanicRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.mechanicRepository = mechanicRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Mechanic findByName(String name) {
        return mechanicRepository.findByName(name);
    }

    @Override
    public void saveMechanic(Mechanic mechanic) {
        mechanic.setPassword(passwordEncoder.encode(mechanic.getPassword()));
        mechanic.setEnabled(1);
        Role mechRole = roleRepository.findByName("ROLE_MECH");
        mechanic.setRoles(new HashSet<Role>(Arrays.asList(mechRole)));
        mechanicRepository.save(mechanic);
    }

}