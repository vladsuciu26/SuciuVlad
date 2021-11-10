package tema11_Streams;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static List<Student> students = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        String infileName = "C:/Users/vlads/Desktop/ScInformala/ProiecteGit/" +
                "SuciuVlad/src/main/resources/students.txt";
        String outfileName = "C:/Users/vlads/Desktop/ScInformala/ProiecteGit/" +
                "SuciuVlad/src/main/resources/output.txt";
        int month = 9;

        txtStudents(infileName);

        String collect = students.stream()
                .filter(student -> student.getMonth() == month)
                .map(student1 -> student1.getFirstName() + " " + student1.getLastName())
                .sorted()
                .collect(Collectors.joining("\n"));
        System.out.println(collect);

        printStudentsInOutputFile(outfileName, collect);
    }


    public static void txtStudents(String inFile) {
        Path fileIn = new File(inFile).toPath();

        try (BufferedReader reader = Files.newBufferedReader(fileIn)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                students.add(getStudentFromTxtLine(line));
            }
        } catch (IOException x) {
            System.err.println("IOException: " + x);
        }
        System.out.println(students.size() + " students");

    }

    public static void printStudentsInOutputFile(String outFile, String stringToPrint) {
        BufferedWriter bw = null;
        try {
            File file = new File(outFile);
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            bw.write("------ Students sorted alphabetically which have month indicated ------\n");
            bw.newLine();
            bw.write(stringToPrint);

        } catch (IOException e) {
            System.err.println("IOException: " + e);
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    public static Student getStudentFromTxtLine(String line) {
        String [] tokens = line.split(",");
        if (tokens.length != 3) {
            return null;
        }

        return new Student(tokens[0], tokens[1], tokens[2]);
    }
}
