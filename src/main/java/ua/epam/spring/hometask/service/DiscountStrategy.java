package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    int getDiscountStrategy(User user, LocalDateTime date, long numberOfTickets);
}
