package ca.dal.csci3130.palm.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Major {
	
	@Id
	private String code;
	
	private String name;
	
	@OneToMany(mappedBy = "major")
	private Set<Student> students;

	public Major(String code, String name) {
		super();
		this.code = code;
		this.name = name;
		this.students = new HashSet<Student>();
	}
	
	Major() {
		// Needed for JPA
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Set<Student> getStudents() {
		return students;
	}
}
