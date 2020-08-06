package pl.dominik.cmms.service.security;

import pl.dominik.cmms.entity.security.User;

public interface UserService {
    User findByUserName(String name);

    User findById(Long id);

    User saveUser(User user);

    void saveMechanic(User user);

    void saveAdmin(User user);
}