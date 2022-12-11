package edu.nus.iss.Clubmvc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.nus.iss.Clubmvc.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c from Course c WHERE c.employeeId = :eid")
    List<Course> findCoursesByEID(@Param("eid") String eid);

    @Query("SELECT c from Course c WHERE c.employeeId = :eid AND (c.status = 'SUBMITTED' OR c.status = 'UPDATED')")
    List<Course> findPendingCoursesByEID(@Param("eid") String eid);

    @Query(value = "SELECT * FROM Course WHERE status = ?0", nativeQuery = true)
    List<Course> findPendingCoursesByStatus(String status);
}
