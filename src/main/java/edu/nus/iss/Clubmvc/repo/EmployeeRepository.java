package edu.nus.iss.Clubmvc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import edu.nus.iss.Clubmvc.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
    @Query("SELECT e FROM Employee e where e.employeeId = :id")
    Employee findEmployeeById(@Param("id") String id);

    @Query("SELECT e FROM Employee e where e.managerId = :mgrid")
    List<Employee> findEmployeeByManagerId(@Param("mgrid") String mgrid);

    @Query("SELECT DISTINCT m FROM Employee e, Employee m where e.managerId = m.employeeId")
    List<Employee> findAllManagers();

    @Query("SELECT DISTINCT m.name FROM Employee e, Employee m where e.managerId = m.employeeId")
    List<String> findAllMangerNames();

    @Query("SELECT DISTINCT e2 FROM Employee e1, Employee e2 WHERE e1.employeeId = e2.managerId AND e1.employeeId = :eid")
    List<Employee> findSubordinates(@Param("eid") String eid);

    @Query("SELECT DISTICT e.employeeId FROM Employee e")
    List<String> findAllEmployeeIDs();
}
