package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.HashSet;
import java.util.Set;

public class AuditoriumDao {

    private static Set<Auditorium> auditoriums = new HashSet<>();

    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    public static void setAuditoriums(Set<Auditorium> auditoriums) {
        AuditoriumDao.auditoriums = auditoriums;
    }
}
