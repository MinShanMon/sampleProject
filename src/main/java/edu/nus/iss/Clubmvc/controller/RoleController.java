package edu.nus.iss.Clubmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.nus.iss.Clubmvc.model.UserSession;

@Controller
@RequestMapping(value="/admin/role")
@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class RoleController {
        
}
