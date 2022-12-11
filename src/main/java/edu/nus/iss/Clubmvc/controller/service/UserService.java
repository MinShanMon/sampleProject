package edu.nus.iss.Clubmvc.controller.service;

import java.util.List;

import edu.nus.iss.Clubmvc.model.Role;
import edu.nus.iss.Clubmvc.model.User;

public interface UserService {
    List<User> findAllUsers();
    User findUser(String userId);
    User createUser(User user);
    User changeUser(User user);
    void removeUser(User user);

    List<Role> findRolesForUser(String userId);
    List<String> findRoleNamesForUser(String userId);
    List<String> findManagerNameByUID(String userId);

    User authenticate(String username, String pwd);
}
