package ca.dal.csci3130.palm.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Major {
	
	@Id
	private String code;
	
	private String name;
	

	public Major(String code, String name) {
		super();
		this.code = code;
		this.name = name;
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

}
