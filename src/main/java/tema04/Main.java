package tema04;

public class Main {
    public static void main(String[] args) {
        String[] myString = {"Learn", "to", "code."};
        StringBuilder concatenateString = new StringBuilder();

        for(int i = 0; i < myString.length; ++i){
            concatenateString.append(myString[i]);
        }

        System.out.println(concatenateString);
    }
}
