package tema10_IO;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testCsvInput() {
        //given
        String inputLine = "1,Suciu Vlad,RO,26:26,xxxox,xxxxx,xxoxo";

        //then
        Athlete result = Main.getAthletesFromCsvLine(inputLine);

        //when
        assert result != null;
        assertEquals(1, result.getAthleteNumber());
        assertEquals("Suciu Vlad", result.getAthleteName());
        assertEquals("RO", result.getCountryCode());
        assertEquals("26:26", result.getSkiTimeResult());
        assertEquals("xxxox", result.getFirstShootingRange());
        assertEquals("xxxxx", result.getSecondShootingRange());
        assertEquals("xxoxo", result.getThirdShootingRange());

    }

    @Test
    public void testCsvInputWithWhitespace() {
        //given
        String inputLine = "1 ,Suciu Vlad,RO,26:26,xxxox,xxxxx,xxoxo";

        //then
        Athlete result = Main.getAthletesFromCsvLine(inputLine);

        //when
        assertNotNull(result);
        assertEquals(1, result.getAthleteNumber());
        assertEquals("Suciu Vlad", result.getAthleteName());
        assertEquals("RO", result.getCountryCode());
        assertEquals("26:26", result.getSkiTimeResult());
        assertEquals("xxxox", result.getFirstShootingRange());
        assertEquals("xxxxx", result.getSecondShootingRange());
        assertEquals("xxoxo", result.getThirdShootingRange());

    }

    @Test
    public void testMalformedCsvInput() {
        //given
        String inputLine = "1 ,Suciu Vlad,RO,xxxox,xxxxx,xxoxo";

        //then
        Athlete result = Main.getAthletesFromCsvLine(inputLine);

        //when
        assertNull(result);

    }

    @Test
    public void testAthletesFinalTime() {
        //given
        Athlete athleteToTest = new Athlete(1, "Suciu Vlad","Ro", "26:26",
                "xxxox", "xxxxx", "xxoxo");

        //when
        Athlete result = Main.updateAthleteTime(athleteToTest);

        //then
        assertEquals(1, result.getAthleteNumber());
        assertEquals("Suciu Vlad", result.getAthleteName());
        assertEquals("RO", result.getCountryCode());
        assertEquals("26:56", result.getSkiTimeResult());
        assertEquals("xxxox", result.getFirstShootingRange());
        assertEquals("xxxxx", result.getSecondShootingRange());
        assertEquals("xxoxo", result.getThirdShootingRange());
    }

    @Test
    public void testMissingShots() {
        //given
        String shots = "xxxox,xxxxx,xxoxo";

        //when
        long countingOccurrence = Main.countOccurrenceOfMissedShots(shots);

        //then
        assertEquals(3, countingOccurrence);
    }
}