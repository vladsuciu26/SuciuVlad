package tema12_Concurrency;

public class FestivalStatisticsThread extends Thread{
    int peopleWithFullTicket;
    int peopleWithFullVipTicket;
    int peopleWithFreePassTicket;
    int peopleWithOneDayTicket;
    int peopleWithOneDayVipTicket;
    int numberOfTotalPeople;

    FestivalGate gate;

    public FestivalStatisticsThread(FestivalGate gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        while(numberOfTotalPeople < 100) {
            resetStatistic();
            numberOfEachTicketType();
            numberOfPeople();
            displayStatistics();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void numberOfEachTicketType() {
        synchronized (this.gate.getValidatedTickets()) {
            for (TicketType ticketType : this.gate.getValidatedTickets()) {
                if(ticketType.equals(TicketType.FULL)) {
                    peopleWithFullTicket++;
                } else if (ticketType.equals(TicketType.FULL_VIP)) {
                    peopleWithFullVipTicket++;
                } else if (ticketType.equals(TicketType.FREE_PASS)) {
                    peopleWithFreePassTicket++;
                } else if (ticketType.equals(TicketType.ONE_DAY)) {
                    peopleWithOneDayTicket++;
                } else if (ticketType.equals(TicketType.ONE_DAY_VIP)) {
                    peopleWithOneDayVipTicket++;
                }

            }
        }

    }

    private void resetStatistic() {
        peopleWithFullTicket = 0;
        peopleWithFullVipTicket = 0;
        peopleWithFreePassTicket = 0;
        peopleWithOneDayTicket = 0;
        peopleWithOneDayVipTicket = 0;
        numberOfTotalPeople = 0;
    }

    public void numberOfPeople() {
        numberOfTotalPeople += peopleWithFullTicket + peopleWithFullVipTicket +
                              peopleWithFreePassTicket + peopleWithOneDayTicket + peopleWithOneDayVipTicket;
    }

    public void displayStatistics() {
        System.out.println(numberOfTotalPeople + " people have entered \n" +
                            peopleWithFullTicket + " have full tickets \n" +
                            peopleWithFreePassTicket + " have free passes \n" +
                            peopleWithFullVipTicket + " have full VIP passes\n" +
                            peopleWithOneDayTicket + " have one-day passes \n" +
                            peopleWithOneDayVipTicket + " have one-day VIP passes \n");

        System.out.println("=-------------------=");
    }
}
