package edu.nus.iss.Clubmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.nus.iss.Clubmvc.controller.service.EmployeeService;
import edu.nus.iss.Clubmvc.controller.service.UserService;
import edu.nus.iss.Clubmvc.model.Employee;
import edu.nus.iss.Clubmvc.model.User;
import edu.nus.iss.Clubmvc.model.UserSession;

public class CommonController {
    @Autowired
    private UserService userService;
    

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value={"/","/login","/home"}, method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/home/authenticate")
    public String authenticate(@ModelAttribute("user") @Valid User user,
    BindingResult bindingResult, Model model, HttpSession session){
        
        if(bindingResult.hasErrors()){
            return "login";
        }

        User u = userService.authenticate(user.getName(), user.getPassword());
        if(u==null){
            model.addAttribute("loginMessage", "Incorrect username/password");
            return "login";
        }

        UserSession userSession = new UserSession();
        userSession.setUser(u);

        userSession.setEmployee(employeeService.findEmployeeById(u.getEmployeeId()));
        
        List<Employee> subordinates = employeeService.findSubordinates(u.getEmployeeId());
        if(subordinates != null){
            userSession.setSubordinates(subordinates);
        }

        session.setAttribute("usession", userSession);

        List<String> roleIds = u.getRoleIds();

        System.out.println("Roles:");
        roleIds.forEach(System.out::println);

        if(roleIds.contains("admin")){
            return "redirect:/admin/employee/list";
        }

        if(roleIds.contains("manager")){
            return "redirect:/manager/pending";
        }

        return "redirect:/staff/course/history";
        
    }

    @GetMapping("/about")
    public String home(){
        return "about";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/home";
    }
}
