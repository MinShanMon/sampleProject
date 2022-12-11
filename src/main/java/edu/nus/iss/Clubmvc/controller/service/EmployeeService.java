package edu.nus.iss.Clubmvc.controller.service;

import java.util.List;

import edu.nus.iss.Clubmvc.model.Employee;

public interface EmployeeService {
    List<Employee> findEmployeeByManager(String s);
    Employee findEmployeeById(String s);
    List<Employee> findAllEmployees();
    Employee findEmployee(String empid);
    Employee creatEmployee(Employee emp);
    Employee changeEmployee(Employee emp);
    void removeEmployee(Employee emp);
    List<String> findAllManagerNames();
    List<Employee> findAllManagers();
    List<Employee> findSubordinates(String employeeId);
    List<String> findAllEmployeeIDs();
}
