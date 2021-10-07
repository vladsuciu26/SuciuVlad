package tema07OOP;

import java.util.ArrayList;
import java.util.List;

public class Contact{
    private String firstName;
    private String lastName;
    private String trackContact;
    private String phoneNumber;

    private List<Contact> contacts = new ArrayList<>();

    public Contact(String trackContact, String phoneNumber, String firstName, String lastName) {
        this.trackContact = trackContact;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTrackContact() {
        return trackContact;
    }
}
