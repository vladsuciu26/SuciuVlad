package tema11_Streams;

public class Student {
    String firstName;
    String lastName;
    String dateOfBirth;

    public Student(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getMonth() {
        String [] tokens = this.getDateOfBirth().split("-");
        int month = Integer.parseInt(tokens[1]);
        return month;
    }
}
