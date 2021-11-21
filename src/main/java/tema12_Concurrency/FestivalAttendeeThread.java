package tema12_Concurrency;

public class FestivalAttendeeThread extends Thread{
    TicketType ticketType;
    FestivalGate festivalGate;

    public FestivalAttendeeThread(TicketType ticketType, FestivalGate gate) {
        this.ticketType = ticketType;
        this.festivalGate = gate;
    }

    @Override
    public synchronized void run() {
        this.festivalGate.getValidatedTickets().add(ticketType);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
