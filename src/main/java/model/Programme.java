package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Programmes offered by a university
 */
public class Programme {
    /**
     * Name and id of the programme
     */
    private String name;
    private int pID;

    /**
     * Start date of the programme
     */
    private Date startDate;

    /**
     * End date of the programme
     */
    private Date dueDate;

    /**
     * Estimated duration of the course in months
     */
    private int estimatedDuration;

    /**
     * Students allocated to the programme
     */
    private List<Student> enrolled = new ArrayList<Student>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return pID;
    }

    public void setID(int ID) {
        this.pID = ID;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public List<Student> getEnrollments() {
        return enrolled;
    }

    
    public boolean removeEnrolledStudent(Student student) {
    
    	return false;
    	   
    }



    /**
     * Add a new student to the programme
     * @param Student: to enroll  to student in a programme 
     * @return true if the student is successfully enrolled, false otherwise
     */

     public boolean addStudent(Student student, Football football) throws IllegalStudentEnrollException {
        if(startDate ==null){
            throw new IllegalStudentEnrollException("invalid, can't have null date");
        }
        if(student ==null){
            throw new IllegalStudentEnrollException("invalid, can't have null student ");
        }
        if (startDate.before(new Date())) { 
            //System.out.println("Cannot join the course because the due date is over");
            return false;
        }
    
        if (enrolled.size() >= 250) {
            //System.out.println("the course is full");
            return false;
        } 
    
        if(enrolled.contains(student)){
            throw new IllegalStudentEnrollException("invalid, this student has already enrolled ");
            
        }
        else{
            enrolled.add(student);
            football.addAvailStudent(student);
            return true;
        }
    }
}    