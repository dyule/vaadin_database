package ca.dal.csci3130.palm.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {

	@Id
	private String courseNumber;
	
	private String courseName;
	
	@ManyToMany(mappedBy="courses")
	private Set<Student> students;

	public Course(String courseNumber, String courseName) {
		super();
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.students = new HashSet<Student>();
	}
	
	Course() {
		// Needed for JPA
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
		student.getCourses().add(this);
	}
	
	public String getCourseNumber() {
		return courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public Set<Student> getStudents() {
		return students;
	}
}
