package edu.nus.iss.Clubmvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.nus.iss.Clubmvc.controller.service.EmployeeService;
import edu.nus.iss.Clubmvc.controller.service.RoleService;
import edu.nus.iss.Clubmvc.controller.service.UserService;
import edu.nus.iss.Clubmvc.model.Role;
import edu.nus.iss.Clubmvc.model.User;
import edu.nus.iss.Clubmvc.model.UserSession;
import edu.nus.iss.Clubmvc.validator.UserValidator;

@Controller
@RequestMapping(value="/admin/user")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserValidator userValidator;

    @InitBinder("user")
    private void initUserBinder(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

    @RequestMapping(value = "/list")
    public String userListPage(Model model){
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        return "user-list";
    }

    @RequestMapping(value = "/create", method =  RequestMethod.GET)
    public String newUserPage(Model model){
        model.addAttribute("user", new User());
        List<String> eidList = employeeService.findAllEmployeeIDs();
        model.addAttribute("eidlist", eidList);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);

        return "user-new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute @Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            List<String> eidList = employeeService.findAllEmployeeIDs();
            model.addAttribute("eidlist", eidList);
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("roles", roles);

            return "user-new";
        }
        List<Role> newRoleSet = new ArrayList<>();
        user.getRoleSet().forEach(role->{
            Role completRole = roleService.findRole(role.getRoleId());
            newRoleSet.add(completRole);
        });
        user.setRoleSet(newRoleSet);

        userService.createUser(user);        
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editUserPage(@PathVariable("id") String id, Model model){
        User user = userService.findUser(id);
        model.addAttribute("user", user);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);

        return "user-edit";
    }
}
