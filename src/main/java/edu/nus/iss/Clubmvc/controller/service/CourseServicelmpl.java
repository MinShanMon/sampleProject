package edu.nus.iss.Clubmvc.controller.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.nus.iss.Clubmvc.model.Course;
import edu.nus.iss.Clubmvc.repo.CourseRepository;
@Service
public class CourseServicelmpl implements CourseService{

    @Resource
    private CourseRepository CourseRepository;

    @Override
    @Transactional
    public Course changeCourse(Course course) {
        return CourseRepository.saveAndFlush(course);
    }

    @Override
    @Transactional
    public Course createCourse(Course course) {        
        return CourseRepository.saveAndFlush(course);
    }

    @Override
    @Transactional
    public List<Course> findAllCourses() {        
        return CourseRepository.findAll();
    }
    
    @Override
    @Transactional
    public Course findCourse(Integer ceid) {    
        return CourseRepository.findById(ceid).orElse(null);
    }

    @Override
    @Transactional
    public List<Course> findCoursesByEID(String eid) {    
        return CourseRepository.findPendingCoursesByEID(eid);
    }

    @Override
    @Transactional
    public List<Course> findPendingCoursesByEID(String eid) {        
        return CourseRepository.findPendingCoursesByEID(eid);
    }

    @Override
    @Transactional
    public void removeCourse(Course course) {
        CourseRepository.delete(course);
        
    }
    
}
