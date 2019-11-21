package ie.gmit.studentmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentManager implements Serializable{

    private static final long serialVersionUID = 1L;
    
    // Declare a List called students to hold the student objects
	private List<Student> studentList;

	// Constructor
	public StudentManager() {
		// Instantiate a student ArrayList
		studentList = new ArrayList<Student>();
	}

	// Getters and Setters
	public List<Student> getStudents() {
		return studentList;
	}

	public void setStudents(List<Student> studentList) {
		this.studentList = studentList;
	}

	/**
	 * This method adds a Student to the Student List.
	 *
	 * @param student a student object that is to be added to the student list
	 * @return a boolean value indicating if the add was successful		
	 */                     
	public boolean addStudent(Student student) {
		try {
			// Using Collections add method. It returns true if this collection
			// changed as a result of the call
			return studentList.add(student);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deleteStudent(Student student) {
		try {
			// Using Collections remove method. It returns true if this list 
			// contained the specified element
			return studentList.remove(student);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deleteStudentById(String studentId) {
		// Search for the Student by ID
		Student student = findStudentById(studentId);
		// If a Student was found then delete the student
		if (student != null) {
			return deleteStudent(student);
		} else {
			// If no student was found Return false
			return false;
		}
	}

	public Student findStudentById(String studentId) {

		// Loop over (i.e. Iterate over) arrayList for Student type elements in
		// the students ArrayList

		// There are 3 ways you can iterate through a List.
		// 1. For Loop
		// 2. Advanced For Loop
		// 3. Iterator

		// 1. For Loop
//		for (int i = 0; i < studentList.size(); i++) {
//			if (studentList.get(i).getStudentId().equals(studentId)) {
//				return studentList.get(i);
//			}
//		}

		// 2. Advanced For Loop
//		for (Student student : studentList) {
//			// No need to check for null as ArrayList is dynamic and fills holes
//			if (student.getStudentId().equals(studentId)) {
//				return student;
//			}
//		}

		// 3. Iterator
		Iterator<Student> studentIterator = studentList.iterator();
		Student studentObjectHolder;
		while (studentIterator.hasNext()) {
			// Store next Student
			studentObjectHolder = studentIterator.next();
			// Check if studentId equals that of current student object
			if (studentObjectHolder.getStudentId().equals(studentId)) {
				return studentObjectHolder;
			}
		}

		// Return null if Student ID was not found
		return null;
	}

	// Find a list of student by first name
	public List<Student> getStudentsByFirstName(String firstName) {
		// Create a new ArrayList to Hold Students with same names
		List<Student> sameNames = new ArrayList<Student>();
		// Loop over arrayList for Student type elements in the students ArrayList do
		for (Student student : studentList) {
			// If I find a student with the given first name then add to list
			if (student.getFirstName().equalsIgnoreCase(firstName)) {
				sameNames.add(student);
			}
		}
		// Check if list has any students
		if (sameNames.size() > 0) {
			// If students were found then return the list
			return sameNames;
		}
		// If no students were found with that first name then return null
		return null;
	}

	public void loadStudentFile(String pathToFile) {
		File inFile = new File(pathToFile);
		FileReader fileReader = null;
		BufferedReader br = null;
		String record = null;

		try {
			fileReader = new FileReader(inFile);
			br = new BufferedReader(fileReader);
			br.readLine(); //discard first line of csv file
			while ((record = br.readLine()) != null) {
				String[] elements = record.split(",");
				Student newStudent = new Student(elements[0], elements[1], elements[2]);
				this.addStudent(newStudent);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int findTotalStudents() {
		// returns the current number of Students in the ArrayList
		return studentList.size();
	}
	
	public StudentManager loadDB(String dbPath){
    	StudentManager sm = null;
    	try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbPath));
			sm = (StudentManager) in.readObject();
    		in.close();
    	} catch (Exception e) {
    		System.out.print("[Error] Cannont load DB. Cause: ");
    		e.printStackTrace();
    	}
		return sm;
    }

}