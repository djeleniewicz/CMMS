package pl.dominik.cmms.service.security;

import pl.dominik.cmms.entity.security.User;

public interface UserService {
    User findByUserName(String name);

    void saveUser(User user);
}