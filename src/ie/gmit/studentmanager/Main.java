package ie.gmit.studentmanager;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {

	private static final long serialVersionUID = 1L; // Used for serialization
	StudentManager sm = new StudentManager(); // Used for managing students

	@Override
	public void start(Stage primaryStage) {
		// Create TextArea node for bottom of scene 1
		TextArea taMyOutput = new TextArea();
		taMyOutput.setPrefHeight(100); // sets height of the TextArea to 400 pixels
		taMyOutput.setPrefWidth(100); // sets width of the TextArea to 300 pixels

		
        
        //Load DB
		Button buttonLoadDb = new Button("Load Database");
		//buttonSearchStudentByName.setOnAction(e -> sm.addStudent());
		
		//Add Students
		Button buttonAddStudent = new Button("Add Student");
		TextField tfAddStudent = new TextField();
		tfAddStudent.setPromptText("Student ID");
		buttonAddStudent.setOnAction(e -> {
			if(tfAddStudent.getText().trim().equals("")) {//if text field is empty

				taMyOutput.setText("INVALID ID - Please enter a valid ID to add a Student");
			}else {
				Student student = new Student(tfAddStudent.getText());
				sm.addStudent(student);
				tfAddStudent.clear();
			}
		});
		
		//Delete Students
		Button buttonDeleteStudent = new Button("Delete Student");
		TextField tfDeleteStudent = new TextField();
		tfDeleteStudent.setPromptText("Student ID");
		buttonDeleteStudent.setOnAction(e -> {	
			if(tfDeleteStudent.getText().trim().equals("")) {//if text field is empty
				
				taMyOutput.setText("INVALID ID - Please enter a valid ID to delete a Student");
			}else {
				
				String studentId = tfDeleteStudent.getText();
				sm.deleteStudentById(studentId);
				tfAddStudent.clear();
			}
		});
		
		//Search Student by ID
		Button buttonSearchStudentById = new Button("Search Student");
		TextField tfSearchStudentById = new TextField();
		tfSearchStudentById.setPromptText("Student ID");
		//buttonSearchStudentById.setOnAction(e -> sm.addStudent());
		
		//Search Student by First Name
		Button buttonSearchStudentByName = new Button("Search Student");
		TextField tfSearchStudentByName = new TextField();
		tfSearchStudentByName.setPromptText("Student First Name");
		//buttonSearchStudentByName.setOnAction(e -> sm.addStudent());
		
		//Show Total Number of Students
		Button buttonShowTotal = new Button("Show Total Students");
		TextField tfTotalNumberOfStudents = new TextField();
		tfTotalNumberOfStudents.setEditable(false);
		tfTotalNumberOfStudents.setPromptText("0");
		buttonShowTotal.setOnAction(e -> { tfTotalNumberOfStudents.setText(Integer.toString(sm.findTotalStudents()));});

        //Save DB
		Button buttonSaveDb = new Button("Save Database");
        buttonSaveDb.setOnAction(e -> {
            if(sm.findTotalStudents() > 0){
                try{
                    File studentDB = new File("./resources/studentsDB.ser");
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(studentDB));
                    out.writeObject(sm);
                    out.close();
                    taMyOutput.setText("Student Database Saved");

                }catch(Exception exception){
                    System.out.println("[Error] Cannot save DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to save Students DB!");
                }
            }else{
                taMyOutput.setText("ERROR: No Students in List to save!");
            }

        });
        
        //Quit
		Button buttonQuit = new Button("Quit");
		//buttonSearchStudentByName.setOnAction(e -> sm.addStudent());
        
        //Daniels Code

        /*

        // Show total number of students
		Button btnShowTotal = new Button("Show Total Students");
		TextField tfTotalNumberOfStudents = new TextField();

		tfTotalNumberOfStudents.setEditable(false); // This box is not editable. Only displays result.
		tfTotalNumberOfStudents.setPromptText("0");

		btnShowTotal.setOnAction(e -> {

			// Code to run when button is clicked
			tfTotalNumberOfStudents.setText(Integer.toString(sm.findTotalStudents()));

        });

		// Add Student arrangement
		Button btnAddStudent = new Button("Add Student");
		TextField tfStudentID = new TextField();

		tfStudentID.setPromptText("Enter Student ID");

		btnAddStudent.setOnAction(e -> {
			if (tfStudentID.getText().trim().equals("")) { // If text field is empty

				taMyOutput.setText("Invalid");
			} else {

				Student student = new Student(tfStudentID.getText());
				sm.addStudent(student); // Add student to student list
				tfStudentID.clear();
			}
		});

		// Delete Student arrangement
		TextField tfStudentDel = new TextField();
		Button btnDelStudent = new Button("Delete Student");

		tfStudentDel.setPromptText("Enter Student ID");

		btnDelStudent.setOnAction(e -> {

			sm.deleteStudentById(tfStudentDel.getText());

        });

        */

        // Adding and arranging all the nodes in the grid - add(node, column, row)
        
        //Set Up Gridpane
		GridPane gridPane1 = new GridPane();
		gridPane1.add(buttonLoadDb, 0, 0);
		gridPane1.add(buttonAddStudent, 0, 1);
		gridPane1.add(tfAddStudent, 1, 1);
		gridPane1.add(buttonDeleteStudent, 0, 2);
		gridPane1.add(tfDeleteStudent, 1, 2);
		gridPane1.add(buttonSearchStudentById, 0, 3);
		gridPane1.add(tfSearchStudentById, 1, 3);
		gridPane1.add(buttonSearchStudentByName, 0, 4);
		gridPane1.add(tfSearchStudentByName, 1, 4);
		gridPane1.add(buttonShowTotal, 0, 5);
		gridPane1.add(tfTotalNumberOfStudents, 1, 5);
		gridPane1.add(buttonSaveDb, 0, 6);
		gridPane1.add(buttonQuit, 1, 6);
		gridPane1.add(taMyOutput, 0, 7,2,1);
        
        //Daniels Code
        /*
        GridPane gridPane1 = new GridPane();
		gridPane1.add(tfStudentID, 0, 0);
		gridPane1.add(btnAddStudent, 1, 0);
		gridPane1.add(btnShowTotal, 0, 1);
		gridPane1.add(tfTotalNumberOfStudents, 1, 1);
		gridPane1.add(tfStudentDel, 0, 2);
		gridPane1.add(btnDelStudent, 1, 2);
		gridPane1.add(taMyOutput, 0, 3, 2, 1);

        */

		// Preparing the Stage (i.e. the container of any JavaFX application)
		// Create a Scene by passing the root group object, height and width
		Scene scene1 = new Scene(gridPane1, 400, 450);
        // Setting the title to Stage.
        
        if (getParameters().getRaw().size() == 0) {
            primaryStage.setTitle("Student Manager Application");
        } else {
            primaryStage.setTitle(getParameters().getRaw().get(0));
        }
    
        // Setting the scene to Stage
		primaryStage.setScene(scene1);
		// Displaying the stage
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
