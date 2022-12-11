package edu.nus.iss.Clubmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.nus.iss.Clubmvc.controller.service.EmployeeService;
import edu.nus.iss.Clubmvc.controller.service.RoleService;
import edu.nus.iss.Clubmvc.model.Role;
import edu.nus.iss.Clubmvc.model.UserSession;
import edu.nus.iss.Clubmvc.validator.RoleValidator;

@Controller
@RequestMapping(value="/admin/role")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class RoleController {
    @Autowired
    private RoleService rService;
    
    @Autowired
    private RoleValidator rValidator;

    @InitBinder("role")
    private void initRoleBinder(WebDataBinder binder){
        binder.addValidators(rValidator);
    }

    @GetMapping("/list")
    public String roleListPage(Model model){
        List<Role> roleList = rService.findAllRoles();
        model.addAttribute("roleList", roleList);
        return "role-list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newRolePage(Model model){
        model.addAttribute("role", new Role());
        
        return "role-new";
    }
}
