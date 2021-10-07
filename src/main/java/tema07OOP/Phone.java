package tema07OOP;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Phone implements DisplayingCallHistory, DisplayingContacts {
    public static int BATTERY_LIFE = 24; // in hours
    private String phoneNumber;
    private long imei;

    private Contact contact;
    private Message message;
    private Material material;
    private Color color;

    private List<Contact> contacts = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    private List<String> phoneNumberList = new ArrayList<>();

    public Contact addContact(String trackContact, String phoneNumber, String firstName, String lastName) {
        Contact newContact = new Contact(trackContact, phoneNumber, firstName, lastName);
        this.contacts.add(newContact);

        this.phoneNumberList.add(phoneNumber);

        return newContact;
    }

    @Override
    public void listContacts() {
        for(Contact contact : contacts) {
            System.out.println(contact.getTrackContact() + " " + contact.getFirstName() + " " +
                                contact.getLastName());
        }
    }

    public void sendMessage(String phoneNumber, String message){
        if(phoneNumberList.contains(phoneNumber)) {
            Message newMessage = new Message(message);
            this.messages.add(newMessage);
            BATTERY_LIFE -= 1;
        } else {
            System.out.println("Incorrect phone number! Enter another one.");
        }

    }


    public void listMessages(String phoneNumber) {
        for(Message message : messages) {
            System.out.println(message.getMessage());
        }
    }

    public boolean call(String phoneNumber) {
        System.out.println("\nCalling " + phoneNumber);
        BATTERY_LIFE -= 2;
        return true;
    }

    @Override
    public void viewHistory() {
//       for(int i = 0; i <= contacts.size(); ++i) {
//           if(call(phoneNumber)) {
//               System.out.println(phoneNumber);
//           }
//       }
    }

    public String getPhoneNumberList(int i) {
        return phoneNumberList.get(i);
    }
}
