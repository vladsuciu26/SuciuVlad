package tema09_StudExceptionOOP.comparator;

import tema09_StudExceptionOOP.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Comparator;
import java.util.Date;

public class StudentComparatorByBirthDate implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;
        try {
            date1 = formatter1.parse(student1.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Converting obtained Date object to LocalDate object
        Instant instant1 = date1.toInstant();
        ZonedDateTime zone1 = instant1.atZone(ZoneId.systemDefault());
        LocalDate givenDate1 = zone1.toLocalDate();
        //Calculating the difference between given date to current date.
        Period period1 = Period.between(givenDate1, LocalDate.now());


        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = null;
        try {
            date2 = formatter2.parse(student2.getDateOfBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Converting obtained Date object to LocalDate object
        Instant instant2 = date2.toInstant();
        ZonedDateTime zone2 = instant2.atZone(ZoneId.systemDefault());
        LocalDate givenDate2 = zone2.toLocalDate();
        //Calculating the difference between given date to current date.
        Period period2 = Period.between(givenDate2, LocalDate.now());

        return period2Days(period1) - period2Days(period2);
    }

    private int period2Days(Period p) {
        if (p == null) {
            return 0;
        }
        return (p.getYears() * 12 + p.getMonths()) * 30 + p.getDays();
    }
}
