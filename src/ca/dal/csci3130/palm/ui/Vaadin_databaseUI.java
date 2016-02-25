package ca.dal.csci3130.palm.ui;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ca.dal.csci3130.palm.data.Course;
import ca.dal.csci3130.palm.data.Major;
import ca.dal.csci3130.palm.data.Student;

@SuppressWarnings("serial")
@Theme("palm")
public class Vaadin_databaseUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadin_databaseUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private Course uiCourse;
	private Course seCourse;
	private Course acCourse;

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		setDefaultCourses();
		Set<Student> students = generateDefaultStudents();
		
		JPAContainer<Student> studentContainer = JPAContainerFactory.make(Student.class, "palm_db");
		
		for (Student student: students) {
			studentContainer.addEntity(student);
		}
		
		studentContainer.addNestedContainerProperty("major.name");
		
		Table studentTable = new Table("Students", studentContainer);
		studentTable.setVisibleColumns("id", "name", "major.name", "courses");
		layout.addComponent(studentTable);
	}
	
	private void setDefaultCourses() {
		JPAContainer<Course> courses = JPAContainerFactory.make(Course.class, "palm_db");
		Object seId = courses.addEntity(new Course("CSCI 3130", "Software Engineering"));
		Object uiId = courses.addEntity(new Course("CSCI 3160", "User Interface Design"));
		Object acId = courses.addEntity(new Course("CSCI 1106", "Animated Computing"));
		seCourse = courses.getItem(seId).getEntity();
		uiCourse = courses.getItem(uiId).getEntity();
		acCourse = courses.getItem(acId).getEntity();
	}
	
	private Set<Student> generateDefaultStudents() {
		Set<Student> students = new HashSet<Student>();
		
		Major infxMajor = new Major("INFX", "Informatics");
		Major csciMajor = new Major("CSCI", "Computer Science");
		
		Student Alice = new Student("Alice", infxMajor);
		Student Bob = new Student("Bob", csciMajor);
		Student Charlie = new Student("Charlie", infxMajor);
		Student Delta = new Student("Delta", infxMajor);
		Student Eve = new Student("Eve", csciMajor);
		
		students.add(Alice);
		students.add(Bob);
		students.add(Charlie);
		students.add(Delta);
		students.add(Eve);
		
		seCourse.addStudent(Alice);
		seCourse.addStudent(Bob);
		seCourse.addStudent(Charlie);
		uiCourse.addStudent(Alice);
		uiCourse.addStudent(Charlie);
		uiCourse.addStudent(Eve);
		acCourse.addStudent(Bob);
		acCourse.addStudent(Delta);
		
		return students;		
	}

}