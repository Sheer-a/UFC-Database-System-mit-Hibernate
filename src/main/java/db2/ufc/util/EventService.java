package db2.ufc.util;

import db2.ufc.Event;
import db2.ufc.Game;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
public class EventService {
    private final EntityManager em;

    @Autowired
    public EventService(EntityManager em) {
        this.em = em;
    }

    public List<Event> getAllEvents() {
        TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e", Event.class);
        return query.getResultList();
    }

    @Transactional
    public void addEvent(Event event) {
        em.persist(event);
        System.out.println("Event added successfully!");
    }

    @Transactional
    public void deleteEvent(Long id) {
        Event event = em.find(Event.class, id);
        if (event != null) {
            List<Game> games = event.getGames();
            for (Game game : games) {
                em.remove(game);
            }
            event.getGames().clear();
            em.remove(event);
            System.out.println("Event deleted successfully!");
        } else {
            System.out.println("Event not found!");
        }
    }

    @Transactional
    public void updateEvent(Long id) {
        Event event = em.find(Event.class, id);
        if (event != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Event's Name: ");
            event.setName(scanner.next());
            System.out.print("Enter Event's Venue: ");
            event.setVenue(scanner.next());
            System.out.print("Enter Event's Start Date/Time (YYYY-MM-DD HH:MM): ");
            event.setStartDateTime(LocalDateTime.parse(scanner.next()));
            System.out.print("Enter Event's End Date/Time (YYYY-MM-DD HH:MM): ");
            event.setEndDateTime(LocalDateTime.parse(scanner.next()));
            em.merge(event);
            System.out.println("Event updated successfully!");
        } else {
            System.out.println("Event not found!");
        }
    }
}
