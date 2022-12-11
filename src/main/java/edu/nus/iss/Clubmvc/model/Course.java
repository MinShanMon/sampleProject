package edu.nus.iss.Clubmvc.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="course")
public class Course {
    @Id
    @Column(name="courseid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @Basic
    @Column(name = "employeeid")
    private String employeeId;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "organiser")
    private String organiser;

    @Column(name = "fromdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Column(name = "todate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @Basic
    @Column(name = "fees")
    private double fees;

    @Basic
    @Column(name = "gstincluded", columnDefinition = "BIT", length = 1)
    private boolean gstincluded;

    @Basic
    @Column(name = "justification")
    private String justification;

    @Basic
    @Column(name = "status", columnDefinition =  "ENUM('SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', REJECTED')")
    private CourseEventEnum status;

    public Course(){}

    public Course(int courseId){
        this.courseId = courseId;
    }

    public Course(String employeeId, String courseName, String organiser,
    LocalDate fromDate, LocalDate toDate, double fees, boolean gstIncluded,
    String justification, CourseEventEnum status){
        super();
        this.employeeId = employeeId;
        this.courseName = courseName;
        this.organiser = organiser;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fees = fees;
        this.gstincluded = gstIncluded;
        this.justification = justification;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public boolean isGstincluded() {
        return gstincluded;
    }

    public void setGstincluded(boolean gstincluded) {
        this.gstincluded = gstincluded;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public CourseEventEnum getStatus() {
        return status;
    }

    public void setStatus(CourseEventEnum status) {
        this.status = status;
    }

    
}
