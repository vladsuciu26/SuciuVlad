package tema07OOP;

public class Message {
    public static final int MAX_CHARACTERS = 100;
    private String message;

    public Message(String message) {
        if(message.length() < MAX_CHARACTERS) {
            this.message = message;
        } else {
            System.out.println("Enter a shorter message");
        }
    }

    public String getMessage(){
        return message;
    }
}
