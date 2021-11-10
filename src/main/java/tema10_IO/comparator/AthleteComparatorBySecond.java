package tema10_IO.comparator;

import tema10_IO.Athlete;

import java.util.Comparator;

public class AthleteComparatorBySecond implements Comparator<Athlete> {
    @Override
    public int compare(Athlete athlete1, Athlete athlete2) {
        String [] timeForFirstAthlete = athlete1.getSkiTimeResult().split(":");
        int athlete1Second = Integer.parseInt(timeForFirstAthlete[1].trim());

        String [] timeForSecondAthlete = athlete2.getSkiTimeResult().split(":");
        int athlete2Second = Integer.parseInt(timeForSecondAthlete[1].trim());

        return athlete1Second - athlete2Second;
    }
}
