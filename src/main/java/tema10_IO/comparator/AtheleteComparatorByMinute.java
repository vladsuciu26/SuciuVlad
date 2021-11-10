package tema10_IO.comparator;

import tema10_IO.Athlete;

import java.util.Comparator;

public class AtheleteComparatorByMinute implements Comparator<Athlete> {
    @Override
    public int compare(Athlete athlete1, Athlete athlete2) {
        String [] timeForFirstAthlete = athlete1.getSkiTimeResult().split(":");
        int athlete1Minute = Integer.parseInt(timeForFirstAthlete[0].trim());

        String [] timeForSecondAthlete = athlete2.getSkiTimeResult().split(":");
        int athlete2Minute = Integer.parseInt(timeForSecondAthlete[0].trim());

        return athlete1Minute - athlete2Minute;
    }
}
