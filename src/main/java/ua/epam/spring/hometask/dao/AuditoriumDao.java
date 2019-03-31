package ua.epam.spring.hometask.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Set;

@Component
public class AuditoriumDao {

    private Set<Auditorium> auditoriums;

    public Set<Auditorium> getAll() {
        return auditoriums;
    }

    @Autowired
    public void setAuditoriums(Set<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
