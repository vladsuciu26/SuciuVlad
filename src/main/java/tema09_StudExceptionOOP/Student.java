package tema09_StudExceptionOOP;

public class Student {
    String firstName;
    String lastName;
    String dateOfBirth;
    String gender;
    long id;

    public Student(String firstName, String lastName, String dateOfBirth, String gender, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    public String getGender() {
        return  gender;
    }
}
