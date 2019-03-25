package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashSet;
import java.util.Set;

public class BookingDao {

    private static Set<Ticket> tickets = new HashSet<>();

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }


}
