package tema09_StudExceptionOOP;

import tema09_StudExceptionOOP.comparator.StudentComparatorByBirthDate;
import tema09_StudExceptionOOP.comparator.StudentComparatorByLastName;
import tema09_StudExceptionOOP.exception.AddStudentException;
import tema09_StudExceptionOOP.exception.DeleteStudentException;
import tema09_StudExceptionOOP.exception.RetrieveStudentsException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    static StudentRepository studentRepository = new StudentRepository();


    public static void main(String[] args) throws ParseException, RetrieveStudentsException {
        Logger logger = Logger.getLogger(Main.class.getName());
        FileHandler fh;
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("C:/Users/vlads/Desktop/ScInformala/ProiecteGit/SuciuVlad/src/main/java/tema09_StudExceptionOOP/exceptions.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }

        //Pentru primul student am pus anul 2004, pentru a vedea exceptia
        Student student1 = new Student("Vlad", "Suciu", "26-09-2004", "male", 123456);
        Student student2 = new Student("Cristian", "Toma", "24-07-2000", "male", 98765);
        Student student3 = new Student("Adina", "Horj", "13-04-2002", "female", 301928);
        Student student4 = new Student("Laura", "Mocanu", "13-04-1999", "female", 731928);
        Student student5 = new Student("Sergiu", "Vertic", "24-07-2001", "male", 451928);

        /* Am pus zile de nastere diferite pentru ca daca am mai multe persoane de aceeasi varsta, Treeset-ul
            o sa imi afiseze doar o data.
         */

        try {
            studentRepository.addStudent(student1);
            studentRepository.addStudent(student2);
            studentRepository.addStudent(student3);
            studentRepository.addStudent(student4);
            studentRepository.addStudent(student5);
        } catch (AddStudentException e) {
            logger.log(Level.SEVERE, "Date of birth should be between 1900 and 2003!");
        }

        System.out.println("==-- Students sorted by a certain age --==");
        studentRepository.retrieveStudentsByAGivenAge(21);

        System.out.println();
        try {
            studentRepository.deleteStudent(student2);
        } catch (DeleteStudentException e) {
            logger.log(Level.INFO, "Student doesn't exist!");
        }
        studentRepository.displayStudents();

        System.out.println("\n==-- Students sorted by last name --==");
        StudentComparatorByLastName studentComparatorByLastName = new StudentComparatorByLastName();
        TreeSet<Student> studentSetByLastName = new TreeSet<>(studentComparatorByLastName);
        studentSetByLastName.addAll(Arrays.asList(student1, student2, student3, student4, student5));

        for (Student student : studentSetByLastName) {
            System.out.println(student.getLastName());
        }


        System.out.println("\n==-- Students sorted by birth date --==");
        StudentComparatorByBirthDate studentComparatorByBirthDate = new StudentComparatorByBirthDate();
        TreeSet<Student> studentSetByBirthDate = new TreeSet<>(studentComparatorByBirthDate);
        studentSetByBirthDate.addAll(Arrays.asList(student1, student2, student3, student4, student5));

        for (Student student : studentSetByBirthDate) {
            System.out.println(student.getFirstName() + " " + student.getLastName()
                                + " " + student.getDateOfBirth());
        }

    }
}
