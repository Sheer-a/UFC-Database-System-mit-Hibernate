package db2.ufc.util;

import db2.ufc.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

//@Component

public class DatabaseInitializer implements CommandLineRunner {

    //@Autowired
    private EntityManagerFactory emf;

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Create body measurements
            BodyMeasurements bodyMeasurements = new BodyMeasurements(180.0, 75.0, 70.0, 90.0, null);
            em.persist(bodyMeasurements);

            // Create a fighter
            Fighter fighter = createFighter(em, "John", "Doe", "USA", LocalDate.of(1990, 5, 15), "The Rock", bodyMeasurements);
            addFightingTechniques(fighter);

            // Create a referee
            Referee referee = createReferee(em, "Jane", "Smith", "UK", LocalDate.of(1985, 8, 20), LocalDate.of(2025, 12, 31), 5);

            // Create a weight class
            WeightClass weightClass = createWeightClass(em, 70.4, 77.1, "Welterweight");

            // Create a game
            Game game = createGame(em, "Title Fight", "2023-06-25");

            // Create an event
            Event event = createEvent(em, "UFC 500", "Las Vegas", "2023-06-24T18:00", "2023-06-25T01:00");

            // Create an organizer
            Organizer organizer = createOrganizer(em, "UFC Promotions", "Los Angeles", "1234567890");

            // Create a sponsor
            Sponsor sponsor = createSponsor(em, "XYZ Corporation", "New York", "9876543210");

            // Assign relationships between entities
            addFighterToWeightClass(em, fighter, weightClass);
            addFighterToEvent(em, fighter, event);
            addGameToEvent(em, game, event);
            addOrganizerToEvent(em, organizer, event);
            addSponsorToEvent(em, sponsor, event);

            tx.commit();

            System.out.println("CRUD operations completed successfully!");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private Fighter createFighter(EntityManager em, String firstName, String lastName, String nationality,
                                  LocalDate birthday, String nickname, BodyMeasurements bodyMeasurements) {
        Fighter fighter = new Fighter(firstName, lastName, nationality, birthday, nickname, bodyMeasurements);
        em.persist(fighter);
        return fighter;
    }

    private void addFightingTechniques(Fighter fighter) {
        fighter.addFightingTechnique(Participant.FightingTechnique.BOXING,
                Participant.FightingTechnique.MUAY_THAI,
                Participant.FightingTechnique.KARATE);
    }

    private Referee createReferee(EntityManager em, String firstName, String lastName, String nationality,
                                  LocalDate birthday, LocalDate certificationUntil, int valuationRank) {
        Referee referee = new Referee(firstName, lastName, nationality, birthday, certificationUntil, valuationRank);
        em.persist(referee);
        return referee;
    }

    private WeightClass createWeightClass(EntityManager em, double minWeight, double maxWeight, String expression) {
        WeightClass weightClass = new WeightClass(minWeight, maxWeight, expression);
        em.persist(weightClass);
        return weightClass;
    }

    private Game createGame(EntityManager em, String description, String date) {
        Game game = new Game(description, date);
        em.persist(game);
        return game;
    }

    private Event createEvent(EntityManager em, String name, String venue, String startDateTime, String endDateTime) {
        Event event = new Event(name, venue, LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
        em.persist(event);
        return event;
    }

    private Organizer createOrganizer(EntityManager em, String name, String address, String phoneNumber) {
        Organizer organizer = new Organizer(name, address, phoneNumber);
        em.persist(organizer);
        return organizer;
    }

    private Sponsor createSponsor(EntityManager em, String name, String address, String phoneNumber) {
        Sponsor sponsor = new Sponsor(name, address, phoneNumber);
        em.persist(sponsor);
        return sponsor;
    }

    private void addFighterToWeightClass(EntityManager em, Fighter fighter, WeightClass weightClass) {
        weightClass.addFighter(fighter);
        em.persist(weightClass);
    }

    private void addFighterToEvent(EntityManager em, Fighter fighter, Event event) {
        event.addFighter(fighter);
        em.persist(event);
    }

    private void addGameToEvent(EntityManager em, Game game, Event event) {
        game.setEvent(event);
        em.persist(game);
    }

    private void addOrganizerToEvent(EntityManager em, Organizer organizer, Event event) {
        event.setOrganizer(organizer);
        em.persist(event);
    }

    private void addSponsorToEvent(EntityManager em, Sponsor sponsor, Event event) {
        event.addSponsor(sponsor);
        em.persist(event);
    }
}
