package edu.nus.iss.Clubmvc.controller.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.nus.iss.Clubmvc.model.Employee;
import edu.nus.iss.Clubmvc.repo.EmployeeRepository;

@Service
public class EmployeeServicelmpl implements EmployeeService{
    @Resource
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public List<Employee> findEmployeeByManager(String s){
        return employeeRepository.findEmployeeByManagerId(s);
    }

    @Override
    @Transactional
    public Employee findEmployeeById(String s){
        return employeeRepository.findEmployeeById(s);
    }

    @Override
    @Transactional
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public Employee findEmployee(String empid){
        return employeeRepository.findById(empid).orElse(null);
    }

    @Override
    @Transactional
    public Employee creatEmployee(Employee emp){
        return employeeRepository.saveAndFlush(emp);
    }

    @Override
    @Transactional
    public Employee changeEmployee(Employee emp){
        return employeeRepository.saveAndFlush(emp);
    }

    @Override
    @Transactional
    public void removeEmployee(Employee emp){
        employeeRepository.delete(emp);
    }

    @Override
    @Transactional
    public List<String> findAllManagerNames(){
        return employeeRepository.findAllMangerNames();
    }

    @Override
    @Transactional
    public List<Employee> findAllManagers(){
        return employeeRepository.findAllManagers();
    }

    @Override
    @Transactional
    public List<Employee> findSubordinates(String employeeId){
        return employeeRepository.findSubordinates(employeeId);
    }

    @Override
    @Transactional
    public List<String> findAllEmployeeIDs(){
        return employeeRepository.findAllEmployeeIDs();
    }
}
