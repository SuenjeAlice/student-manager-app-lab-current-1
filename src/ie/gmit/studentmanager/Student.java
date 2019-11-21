package ie.gmit.studentmanager;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
    // Instance Variables
	private String studentId;
	private String firstName;
	private String surname;
	private Date dob;
	private Address address;
	private Course course;

	// Constructors
	public Student(String studentId) {
		this.studentId = studentId;
	}
	
	public Student(String studentId, String firstName, String surname) {
		// this(studentId); - could set studentId this way
		this.studentId = studentId;
		this.firstName = firstName;
		this.surname = surname;
	}
	
	public Student(String studentId, String firstName, String surname, Date dob) {
		// this(studentId); - could set studentId this way
		this.studentId = studentId;
		this.firstName = firstName;
		this.surname = surname;
		this.dob = dob;
	}

	// Getters and Setters
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}