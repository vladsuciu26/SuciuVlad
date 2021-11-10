package tema11_Streams;

import org.junit.Test;
import tema11_Streams.Student;
import tema11_Streams.Main;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testTxtInput() {
        //given
        String inputLine = "Bogdan,Berche,13-06-2000";

        //then
        Student result = Main.getStudentFromTxtLine(inputLine);

        //when
        assert result != null;
        assertEquals("Bogdan", result.getFirstName());
        assertEquals("Berche", result.getLastName());
        assertEquals("13-06-2000", result.getDateOfBirth());
    }
}