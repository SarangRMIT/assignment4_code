package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Date;
import java.util.Calendar;

/**
 *  Implement and test {Programme.addStudent } that respects the considtion given the assignment specification
 * NOTE: You are expected to verify that the constraints to add a new student to a programme are met.
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with initialise method.
 */
public class AddStudent {
	
	private Programme programme;
    private Student student1;
    private Football football;

    @BeforeEach
    public void initialize() {
        programme = new Programme();
        programme.setStartDate(new Date());
        //programme.setEstimatedDuration(12);

        student1 = new Student("jon doe", 12345678);
        football = new Football();

    }

    @Test
    @DisplayName("Successful enrolment")
    public void test_EnrollmentSuccess() throws IllegalStudentEnrollException {
        Calendar deadline = Calendar.getInstance();

        deadline.set(2024, Calendar.DECEMBER, 31);

        programme.setStartDate(deadline.getTime());
        boolean check = programme.addStudent(student1, football);

        assertTrue(check);

        assertEquals(1, programme.getEnrollments().size());

        assertTrue(programme.getEnrollments().contains(student1));

        
    }

    @Test
    @DisplayName("Null Student")
    public void adding_null_student(){
        programme.setStartDate(new Date());
        assertThrows(IllegalStudentEnrollException.class, () -> {
            programme.addStudent(null, football);
        });
        assertEquals(0, programme.getEnrollments().size());
    }

    @Test
    @DisplayName("Null Date")
    public void adding_null_date(){
        Date date = null;
        programme.setStartDate(date);
        assertThrows(IllegalStudentEnrollException.class, () -> {
            programme.addStudent(student1, football);
        });
        assertEquals(0, programme.getEnrollments().size());
        
    }

    @Test
    @DisplayName("Enrolling past the date")
    public void testAddStudent_StartDatePassed() throws IllegalStudentEnrollException {
        
        
        Calendar deadline = Calendar.getInstance();

        deadline.set(2023, Calendar.FEBRUARY, 14);

        programme.setStartDate(deadline.getTime());
        boolean check = programme.addStudent(student1, football);

        assertFalse(check);

        assertEquals(0, programme.getEnrollments().size());
    }

    @Test
    @DisplayName("registering on the day")
    public void testAddStudent_StartDate_onTheDay() throws IllegalStudentEnrollException {

        programme.setStartDate(new Date());
        boolean check = programme.addStudent(student1, football);

        assertTrue(check);

        assertEquals(1, programme.getEnrollments().size());

        assertTrue(programme.getEnrollments().contains(student1));
    
    }

    @Test
    @DisplayName("Max capacity")
    public void testAddingStudent_afterMaxCapacity() throws IllegalStudentEnrollException {
        // Enroll 250 students
        for (int i = 1; i <= 250; i++) {
            programme.setStartDate(new Date(System.currentTimeMillis()+10000));
            Student student = new Student("Student" + i, i);
            programme.addStudent(student, football);
        }

        boolean check = programme.addStudent(student1, football);

        assertFalse(check);
        assertEquals(250, programme.getEnrollments().size());
    }

    @Test
    @DisplayName("Duplicate Student")
    public void testDuplicateStudent() throws IllegalStudentEnrollException  {
        
        
        programme.setStartDate(new Date());
        programme.addStudent(student1, football); // Enroll the student initially

        assertThrows(IllegalStudentEnrollException.class, () -> {
            programme.setStartDate(new Date());
            programme.addStudent(student1, football);
        });
        
    }
    @Test
    @DisplayName("adding a student after 249 students enrolled")
    public void Max_capacity_edgecase() throws IllegalStudentEnrollException {
        // Enroll 250 students
        for (int i = 1; i <= 249; i++) {
            programme.setStartDate(new Date(System.currentTimeMillis()+10000));
            Student student = new Student("Student" + i, i);
            programme.addStudent(student, football);
        }

        boolean check = programme.addStudent(student1, football);

        assertTrue(check);
        assertEquals(250, programme.getEnrollments().size());
    }

    @Test
    @DisplayName("Enrolling in a Max capacity class after start date")
    public void testAddingStudent_afterMaxCapacity_and_afterStartDate() throws IllegalStudentEnrollException {
        // Enroll 250 students
        for (int i = 1; i <= 250; i++) {
            programme.setStartDate(new Date(System.currentTimeMillis()+1000));
            Student student = new Student("Student" + i, i);
            programme.addStudent(student, football);
        }
        Calendar deadline = Calendar.getInstance();

        deadline.set(2023, Calendar.FEBRUARY, 14);

        programme.setStartDate(deadline.getTime());
        boolean check = programme.addStudent(student1, football);

        assertFalse(check);
        assertEquals(250, programme.getEnrollments().size());
    }

    @Test
    @DisplayName("Successful enrolment")
    public void test_FootballAvail() throws IllegalStudentEnrollException {
        Calendar deadline = Calendar.getInstance();

        deadline.set(2024, Calendar.DECEMBER, 31);

        programme.setStartDate(deadline.getTime());
        boolean check = programme.addStudent(student1, football);

        assertTrue(check);

        assertTrue(football.getAvailStudent().contains(student1));

        
    }
    

}

