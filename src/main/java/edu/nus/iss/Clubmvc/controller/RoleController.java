package edu.nus.iss.Clubmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.nus.iss.Clubmvc.controller.exception.RoleNotFound;
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

    @RequestMapping(value = "/create", method=RequestMethod.POST)
    public String newRole(@ModelAttribute @Valid Role role, BindingResult reslut, Model model){
        if(reslut.hasErrors()){
            return "role-new";
        }
        rService.createRole(role);

        return "redirect:/admin/role/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEmployeePage(@PathVariable("id") String id, Model model){
        Role role = rService.findRole(id);
        model.addAttribute("role", role);

        return "role-edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editRole(@ModelAttribute @Valid Role role, BindingResult result,
    @PathVariable String id) throws RoleNotFound{
        if(result.hasErrors()){
            return "role-edit";
        }
        rService.changeRole(role);

        return "redirect:/admin/role/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable String id)throws RoleNotFound{
        Role role = rService.findRole(id);
        rService.removeRole(role);

        String message = "The role "+ role.getRoleId()+" was successfully deleted.";
        System.out.println(message);
        
        return "redirect:/admin/role/list";
    }
}
