package edu.nus.iss.Clubmvc.controller.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.nus.iss.Clubmvc.model.Role;
import edu.nus.iss.Clubmvc.repo.RoleRepository;

@Service
public class RoleServicelmpl implements RoleService{
    @Resource
    private RoleRepository roleRepo;

    @Override
    @Transactional
    public List<Role> findAllRoles(){
        return roleRepo.findAll();
    }

    @Override
    @Transactional
    public Role findRole(String roleId){
        return roleRepo.findById(roleId).orElse(null);
    }

    @Override
    @Transactional
    public Role createRole(Role role){
        return roleRepo.saveAndFlush(role);
    }

    @Override
    @Transactional
    public void removeRole(Role role){
        roleRepo.delete(role);
    }

    @Transactional
    @Override
    public List<String> findAllRolesNames(){
        return roleRepo.findAllRolesNames();
    }

    @Override
    @Transactional
    public List<Role> findRoleByName(String name){
        return roleRepo.findRoleByName(name);
    }

    @Override
    public Role changeRole(Role role) {        
        return roleRepo.saveAndFlush(role);
    }    
}
