package tema12_Concurrency;

import tema08_Library_OOP.Novel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FestivalGate {
    List<TicketType> validatedTickets = Collections.synchronizedList(new ArrayList<>());

    public List<TicketType> getValidatedTickets() {
        return validatedTickets;
    }

}
