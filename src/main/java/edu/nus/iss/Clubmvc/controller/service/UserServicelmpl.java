package edu.nus.iss.Clubmvc.controller.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nus.iss.Clubmvc.model.Role;
import edu.nus.iss.Clubmvc.model.User;
import edu.nus.iss.Clubmvc.repo.UserRepository;

@Service
public class UserServicelmpl implements UserService{
    @Autowired
    private UserRepository userREpository;

    @Override
    public User authenticate(String username, String pwd) {        
        return userREpository.findUserByNamePwd(username, pwd);
    }

    @Override
    @Transactional
    public User changeUser(User user) {        
        return userREpository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User createUser(User user) {        
        return userREpository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() {        
        return userREpository.findAll();
    }

    @Override
    public List<String> findManagerNameByUID(String userId) {        
        return userREpository.findManagerNamesByUID(userId);
    }

    @Override
    @Transactional
    public List<String> findRoleNamesForUser(String userId) {
        List<Role> roles = findRolesForUser(userId);
        List<String> roleName = new ArrayList<>();
        roles.forEach(role->roleName.add(role.getName()));
        return roleName;
    }

    @Override
    @Transactional
    public List<Role> findRolesForUser(String userId) {        
        User user = userREpository.findById(userId).orElse(null);
        if(user == null){
            return new ArrayList<Role>();
        }
        return user.getRoleSet();
    }

    @Override
    @Transactional
    public User findUser(String userId) {        
        return userREpository.findById(userId).orElse(null);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        userREpository.delete(user);        
    }
    
    


}
