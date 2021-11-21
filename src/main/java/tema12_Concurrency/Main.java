package tema12_Concurrency;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FestivalGate gate = new FestivalGate();
        TicketType ticketType;
        FestivalAttendeeThread festivalAttendee;
        FestivalStatisticsThread statsThread = new FestivalStatisticsThread(gate);

        statsThread.start();

        for(int i = 1; i <= 100; ++i) {
            ticketType = TicketType.randomTicket();
            festivalAttendee = new FestivalAttendeeThread(ticketType, gate);
            festivalAttendee.start();
            festivalAttendee.join();
        }


    }

}
