package ua.epam.spring.hometask.dao;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookingDao {

    private static Set<Ticket> tickets = new HashSet<>();

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }


}
