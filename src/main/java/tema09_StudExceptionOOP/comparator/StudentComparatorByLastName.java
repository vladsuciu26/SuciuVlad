package tema09_StudExceptionOOP.comparator;

import tema09_StudExceptionOOP.Student;

import java.util.Comparator;

public class StudentComparatorByLastName implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        return student1.getLastName().compareToIgnoreCase(student2.getLastName());
    }
}
