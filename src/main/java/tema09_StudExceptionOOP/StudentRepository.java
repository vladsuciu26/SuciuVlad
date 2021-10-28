package tema09_StudExceptionOOP;

import tema09_StudExceptionOOP.exception.AddStudentException;
import tema09_StudExceptionOOP.exception.DeleteStudentException;
import tema09_StudExceptionOOP.exception.RetrieveStudentsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository {
    List<Student> students = new ArrayList<>();
    Student student;

    public void addStudent(Student studentToAdd) throws ParseException, AddStudentException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse(studentToAdd.getDateOfBirth());
        //Converting obtained Date object to LocalDate object
        Instant instant = date.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        //Calculating the difference between given date to current date.
        Period period = Period.between(givenDate, LocalDate.now());

        if (!(period.getYears() < 121 && period.getYears() > 18)) { // 121 = 2021 - 1900
            throw new AddStudentException("Date of birth should be between 1900 and 2003!");
        }
        if (studentToAdd.getFirstName().isEmpty() || studentToAdd.getLastName().isEmpty()) {
            throw new AddStudentException("First and last name should not be empty!");
        }

        Set<String> genders = new HashSet<String>();
        genders.add("male");
        genders.add("m");
        genders.add("MALE");
        genders.add("M");
        genders.add("female");
        genders.add("f");
        genders.add("FEMALE");
        genders.add("F");
        if(!genders.contains(studentToAdd.getGender())) {
            throw new AddStudentException("The gender entered is not spelled correctly!");
        }

        students.add(studentToAdd);
    }

    public void deleteStudent(Student studentToDelete) throws DeleteStudentException {
        if (!students.contains(studentToDelete)) {
            throw  new DeleteStudentException("Student doesn't exist!");
        }
        if (studentToDelete.getId() == 0) {
            throw new DeleteStudentException("Id is empty!");
        }

        students.remove(studentToDelete);
    }

    public void retrieveStudentsByAGivenAge(int age) throws ParseException, RetrieveStudentsException {
        if  (age < 0) {
            throw new RetrieveStudentsException("Age is negative");
        }

        for (Student student : students) {
            //Converting String to Date
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(student.getDateOfBirth());
            //Converting obtained Date object to LocalDate object
            Instant instant = date.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate givenDate = zone.toLocalDate();
            //Calculating the difference between given date to current date.
            Period period = Period.between(givenDate, LocalDate.now());
            if (age == period.getYears()) {
                System.out.print(student.getLastName() + " " + student.getFirstName() + " ");
                System.out.println(period.getYears());
            }
        }
    }

    public void displayStudents() {
        for(Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + " " +
                                student.getDateOfBirth() + " " + student.getGender() + " " +
                                student.getId());
        }
    }

    public static Date StringToDate(String dob) throws ParseException{
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(dob);
        return date;
    }

}
