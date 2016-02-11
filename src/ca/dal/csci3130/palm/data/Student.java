package ca.dal.csci3130.palm.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	public Student(String name, Major major) {
		super();
		this.name = name;
		this.major = major;
		major.getStudents().add(this);
		this.courses = new HashSet<Course>();
	}
	
	Student() {
		// Needed for JPA
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@ManyToOne
	private Major major;
	
	@ManyToMany
	private Set<Course> courses;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Major getMajor() {
		return major;
	}

	public Set<Course> getCourses() {
		return courses;
	}
}
