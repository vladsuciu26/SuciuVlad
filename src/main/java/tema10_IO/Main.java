package tema10_IO;

import tema10_IO.comparator.AtheleteComparatorByMinute;
import tema10_IO.comparator.AthleteComparatorBySecond;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/* x - hit
   o - miss
 */

public class Main {
    static List<Athlete> athletes = new ArrayList<>();

    public static void main(String[] args) {

        csvAthletes();

    }

    private static void csvAthletes() {
        Athlete athlete;
        Athlete updatedAthlete;

        Path fileIn = new File("C:/Users/vlads/Desktop/ScInformala/ProiecteGit/" +
                                        "SuciuVlad/src/main/resources/athletes.csv").toPath();

        AtheleteComparatorByMinute athleteComparatorByMinute = new AtheleteComparatorByMinute();
        AthleteComparatorBySecond athleteComparatorBySecond = new AthleteComparatorBySecond();
        TreeSet<Athlete> athleteSetByTime = new TreeSet<>(athleteComparatorByMinute
                .thenComparing(athleteComparatorBySecond));

        try (BufferedReader reader = Files.newBufferedReader(fileIn)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
//                Athlete athlete;
//                Athlete updatedAthlete;
                System.out.println(line);

                athlete = getAthletesFromCsvLine(line);
                assert athlete != null;
                updatedAthlete = updateAthleteTime(athlete);

                athleteSetByTime.add(updatedAthlete);
            }

        } catch (IOException e) {
            System.err.println("IOException: " + e);
        }

        System.out.println("\n------ Athletes sorted by final time ------");
        for (Athlete athleteSorted : athleteSetByTime) {
            System.out.println(athleteSorted.getAthleteName() + " " + athleteSorted.getSkiTimeResult());
        }
    }

    public static Athlete getAthletesFromCsvLine(String line) {
        String [] tokens = line.split(",");
        if  (tokens.length != 7) {
            return null;
        }
        int athleteNumber = Integer.parseInt(tokens[0].trim());
        return new Athlete(athleteNumber, tokens[1], tokens[2], tokens[3], tokens[4],
                           tokens[5], tokens[6]);
    }

    public static Athlete updateAthleteTime(Athlete newAthlete) {
        String s = String.format("%s,%s,%s", newAthlete.getFirstShootingRange(), newAthlete.getSecondShootingRange(), newAthlete.getThirdShootingRange());

        String [] time = newAthlete.getSkiTimeResult().split(":");
        int minute = Integer.parseInt(time[0].trim());
        int second = Integer.parseInt(time[1].trim());

        long countO = countOccurrenceOfMissedShots(s);

        //Daca sunt 5 de 'o' => 50 sec penalizare (+50sec)
        if (countO >= 6) { //Daca sunt mai mult de 6 de 'o'(adica peste 60 sec)
            minute += 1;
        } else {
            second += countO * 10;
        }

        String stringForMinutesAndSeconds = String.format("%s:%s", minute, second);

        return new Athlete(newAthlete.getAthleteNumber(), newAthlete.getAthleteName(), newAthlete.getCountryCode(),
                stringForMinutesAndSeconds, newAthlete.getFirstShootingRange(), newAthlete.getSecondShootingRange(),
                newAthlete.getThirdShootingRange());
    }

    public static long countOccurrenceOfMissedShots(String s) {
        char search = 'o';
        long countMissedShots = s.chars().filter(ch -> ch == search).count();
        return countMissedShots;
    }
}
