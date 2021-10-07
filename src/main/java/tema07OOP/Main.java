package tema07OOP;

import tema07OOP.Samsung.Samsung;

/*Metoda viewHistory() nu am reusit sa o fac
* si la crearea celor 2 telefoane am incercat sa fac ceva, dar nu
* precum cerinta, asa ca obiectul creat e de tip Samsung,
* nu SamsungGalaxy6, precum zice cerinta*/

public class Main {
    public static void main(String[] args) {
        Phone phone = new Samsung();

        phone.addContact("1", "0784224630", "Vlad", "Suciu");
        phone.addContact("2", "0739918321", "Michael", "Jordan");

        phone.listContacts();

        System.out.println();

        phone.sendMessage("0784224630", "Hi Vlad, how are you?");
        phone.sendMessage("0784224630", "I want to go outside.");

        System.out.println("Messages for number " + phone.getPhoneNumberList(0));
        phone.listMessages(phone.getPhoneNumberList(0));

        phone.call(phone.getPhoneNumberList(1));
        phone.viewHistory();
    }
}
