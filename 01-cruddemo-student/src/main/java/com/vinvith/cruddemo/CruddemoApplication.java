package com.vinvith.cruddemo;

import com.vinvith.cruddemo.dao.StudentDAO;
import com.vinvith.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner-> {
		//	createStudent(studentDAO);
			createMultipleStudents(studentDAO);
		//	readStudent(studentDAO);
		//	queryForStudents(studentDAO);
		//	queryForStudentsByLastName(studentDAO);
		//	updateStudent(studentDAO);
		//	deleteStudent(studentDAO);
		//	deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count : "+numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Getting student with id : "+studentId);
		Student myStudent = studentDAO.findById(studentId);
		//display the student which we are going to delete
		System.out.println("Student going to be deleted : "+myStudent);
		System.out.println("Deleting student with id : "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id : "+studentId);
		Student myStudent = studentDAO.findById(studentId);

		//change first name to "John"
		System.out.println("Updating student ...");
		myStudent.setFirstName("John");

		//Update the student
		studentDAO.update(myStudent);

		//display the updated student
		System.out.println("Found the student : "+myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");
		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}


	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findAll();
		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Daffy", "Doe", "Daffy@gmail.com");
		//save the student
		System.out.println("Saving Student object...");
		studentDAO.save(tempStudent);
		//display the id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: "+theId);
		//retrieve student based on the id : primary key
		System.out.println("Retrieving student with id : "+theId);
		Student myStudent = studentDAO.findById(theId);
		//display student
		System.out.println("Found the student : "+myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating new student objects...");
		Student tempStudent1 = new Student("John", "Doe", "John@gmail.com");
		Student tempStudent2 = new Student("Mary", "Doe", "Mary@gmail.com");
		Student tempStudent3 = new Student("Bonita", "Doe", "Bonita@gmail.com");

		//save the student object
		System.out.println("Saving Student object...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");

		//save the student object
		System.out.println("Saving Student object...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("Saved student. Generated id: "+tempStudent.getId());
	}
}
