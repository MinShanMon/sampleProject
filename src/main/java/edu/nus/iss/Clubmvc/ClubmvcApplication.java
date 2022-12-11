package edu.nus.iss.Clubmvc;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.nus.iss.Clubmvc.model.Course;
import edu.nus.iss.Clubmvc.model.CourseEventEnum;
import edu.nus.iss.Clubmvc.model.Employee;
import edu.nus.iss.Clubmvc.model.Role;
import edu.nus.iss.Clubmvc.model.User;
import edu.nus.iss.Clubmvc.repo.CourseRepository;
import edu.nus.iss.Clubmvc.repo.EmployeeRepository;
import edu.nus.iss.Clubmvc.repo.RoleRepository;
import edu.nus.iss.Clubmvc.repo.UserRepository;

@SpringBootApplication
public class ClubmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubmvcApplication.class, args);
	}
	

}
