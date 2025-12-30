package db2.ufc.util;

import db2.ufc.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
public class CRUDProgram {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    private final FighterService fighterService;
    private final RefereeService refereeService;
    private final EventService eventService;


    @Autowired
    public CRUDProgram(EntityManagerFactory emf, EntityManager em, FighterService fighterService, RefereeService refereeService, EventService eventService) {
        this.emf = emf;
        this.em = em;
        this.fighterService = fighterService;
        this.refereeService = refereeService;
        this.eventService = eventService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int choice = showMenu(scanner);
            switch (choice) {
                case 1:
                    listAllFighters();
                    break;
                case 2:
                    listAllReferees();
                    break;
                case 3:
                    listAllEvents();
                    break;
                case 4:
                    listAllEntities();
                    break;
                case 5:
                    addFighter(scanner);
                    break;
                case 6:
                    addReferee(scanner);
                    break;
                case 7:
                    addEvent(scanner);
                    break;
                case 8:
                    deleteFighter(scanner);
                    break;
                case 9:
                    deleteReferee(scanner);
                    break;
                case 10:
                    deleteEvent(scanner);
                    break;
                case 11:
                    updateFighter(scanner);
                    break;
                case 12:
                    updateReferee(scanner);
                    break;
                case 13:
                    updateEvent(scanner);
                    break;
                case 14:
                    exitProgram();
            }
        }
    }

    private int showMenu(Scanner scanner) {
        System.out.println("CRUD Program");
        System.out.println("1. List all Fighters");
        System.out.println("2. List all Referees");
        System.out.println("3. List all Events");
        System.out.println("4. List all entities");
        System.out.println("5. Add a Fighter");
        System.out.println("6. Add a Referee");
        System.out.println("7. Add an Event");
        System.out.println("8. Delete a Fighter");
        System.out.println("9. Delete a Referee");
        System.out.println("10. Delete an Event");
        System.out.println("11. Update a Fighter");
        System.out.println("12. Update a Referee");
        System.out.println("13. Update an Event");
        System.out.println("14. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void listAllFighters() {
        List<Fighter> fighters = fighterService.getAllFighters();
        System.out.printf("+-----+------------------+------------------+-----------------+------------+----------------------+-------------------------------------------------------------------------------+%n");
        System.out.printf("| %-4s| %-18s| %-18s| %-17s| %-12s| %-17s| %-78s|%n", "ID", "First Name", "Last Name", "Nationality", "Birthday", "Nickname", "Body Measurements");
        System.out.printf("+-----+------------------+------------------+-----------------+------------+----------------------+-------------------------------------------------------------------------------+%n");
        for (Fighter fighter : fighters) {
            BodyMeasurements measurements = fighter.getBodyMeasurements();
            String measurementsString = String.format("Height: %.2f; Weight: %.2f; Arm Length: %.2f; Leg Length: %.2f",
                    measurements.getHeight(), measurements.getWeight(), measurements.getArmLength(), measurements.getLegLength());

            System.out.printf("| %-4d| %-18s| %-18s| %-17s| %-12s| %-17s| %-78s|%n",
                    fighter.getPid(), fighter.getFirstname(), fighter.getLastname(),
                    fighter.getNationality(), fighter.getBirthday(), fighter.getNickname(), measurementsString);
        }
        System.out.printf("+-----+------------------+------------------+-----------------+------------+----------------------+-------------------------------------------------------------------------------+%n");
    }

    private void listAllReferees() {
        List<Referee> referees = refereeService.getAllReferees();
        System.out.println("\nReferees");
        System.out.printf("+-----+------------------+------------------+-----------------+------------+---------------------+%n");
        System.out.printf("| %-4s| %-18s| %-18s| %-17s| %-12s| %-16s|%n", "ID", "First Name", "Last Name", "Nationality", "Birthday", "Valuation Rank");
        System.out.printf("+-----+------------------+------------------+-----------------+------------+---------------------+%n");
        for (Referee referee : referees) {
            System.out.printf("| %-4d| %-18s| %-18s| %-17s| %-12s| %-16d|%n",
                    referee.getPid(), referee.getFirstname(), referee.getLastname(),
                    referee.getNationality(), referee.getBirthday(), referee.getValuationRank());
        }
        System.out.printf("+-----+------------------+------------------+-----------------+------------+---------------------+%n");
        System.out.println();

    }

    private void listAllEvents() {
        List<Event> events = eventService.getAllEvents();
        System.out.println("\nEvents");
        System.out.printf("+-----+---------------------+-----------------+-------------------------+---------------------------+%n");
        System.out.printf("| %-4s| %-20s| %-16s| %-25s| %-25s|%n", "ID", "Name", "Venue", "Start Date/Time", "End Date/Time");
        System.out.printf("+-----+---------------------+-----------------+-------------------------+---------------------------+%n");
        for (Event event : events) {
            System.out.printf("| %-4d| %-20s| %-16s| %-25s| %-25s|%n",
                    event.getEid(), event.getName(), event.getVenue(),
                    event.getStartDateTime(), event.getEndDateTime());
        }
        System.out.printf("+-----+---------------------+-----------------+-------------------------+---------------------------+%n");
        System.out.println();
    }

    private void listAllEntities() {
        listAllFighters();
        System.out.println();
        listAllReferees();
        System.out.println();
        listAllEvents();
    }


    @Transactional
    public void addFighter(Scanner scanner) {
        System.out.print("Enter Fighter's First Name: ");
        String firstname = scanner.next();
        System.out.print("Enter Fighter's Last Name: ");
        String lastname = scanner.next();
        System.out.print("Enter Fighter's Nationality: ");
        String nationality = scanner.next();
        System.out.print("Enter Fighter's Birthday (YYYY-MM-DD): ");
        LocalDate birthday = LocalDate.parse(scanner.next());
        System.out.print("Enter Fighter's Nickname: ");
        String nickname = scanner.next();

        System.out.print("Enter Fighter's Height: ");
        double height = scanner.nextDouble();
        System.out.print("Enter Fighter's Weight: ");
        double weight = scanner.nextDouble();
        System.out.print("Enter Fighter's Arm Length: ");
        double armLength = scanner.nextDouble();
        System.out.print("Enter Fighter's Leg Length: ");
        double legLength = scanner.nextDouble();

        BodyMeasurements bodyMeasurements = new BodyMeasurements(height, weight, armLength, legLength, null);

        Fighter fighter = new Fighter(firstname, lastname, nationality, birthday, nickname, bodyMeasurements);
        fighterService.addFighter(fighter, bodyMeasurements);
        System.out.println("Fighter added successfully!");
    }


    private void addReferee(Scanner scanner) {
        System.out.print("Enter Referee's First Name: ");
        String firstname = scanner.next();
        System.out.print("Enter Referee's Last Name: ");
        String lastname = scanner.next();
        System.out.print("Enter Referee's Nationality: ");
        String nationality = scanner.next();
        System.out.print("Enter Referee's Birthday (YYYY-MM-DD): ");
        LocalDate birthday = LocalDate.parse(scanner.next());
        System.out.print("Enter Referee's Certification Until (YYYY-MM-DD): ");
        LocalDate certificationUntil = LocalDate.parse(scanner.next());
        System.out.print("Enter Referee's Valuation Rank: ");
        int valuationRank = scanner.nextInt();

        Referee referee = new Referee(firstname, lastname, nationality, birthday, certificationUntil, valuationRank);
        refereeService.addReferee(referee);
        System.out.println("Referee added successfully!");
    }

    private void addEvent(Scanner scanner) {
        System.out.print("Enter Event's Name: ");
        String name = scanner.next();
        System.out.print("Enter Event's Venue: ");
        String venue = scanner.next();
        System.out.print("Enter Event's Start Date/Time (YYYY-MM-DD HH:MM): ");
        LocalDateTime startDateTime = LocalDateTime.parse(scanner.next());
        System.out.print("Enter Event's End Date/Time (YYYY-MM-DD HH:MM): ");
        LocalDateTime endDateTime = LocalDateTime.parse(scanner.next());

        Event event = new Event(name, venue, startDateTime, endDateTime);
        eventService.addEvent(event);
        System.out.println("Event added successfully!");
    }

    private void deleteFighter(Scanner scanner) {
        System.out.print("Enter Fighter's ID to delete: ");
        Long id = scanner.nextLong();
        fighterService.deleteFighter(id);
    }

    private void deleteReferee(Scanner scanner) {
        System.out.print("Enter Referee's ID to delete: ");
        Long id = scanner.nextLong();
        refereeService.deleteReferee(id);
    }

    private void deleteEvent(Scanner scanner) {
        System.out.print("Enter Event's ID to delete: ");
        Long id = scanner.nextLong();
        eventService.deleteEvent(id);
    }

    private void updateFighter(Scanner scanner) {
        System.out.print("Enter Fighter's ID to update: ");
        Long id = scanner.nextLong();
        fighterService.updateFighter(id);
    }

    private void updateReferee(Scanner scanner) {
        System.out.print("Enter Referee's ID to update: ");
        Long id = scanner.nextLong();
        refereeService.updateReferee(id);
    }

    private void updateEvent(Scanner scanner) {
        System.out.print("Enter Event's ID to update: ");
        Long id = scanner.nextLong();
        eventService.updateEvent(id);
    }

    private void exitProgram() {
        System.out.println("Exiting program...");
        em.close();
        emf.close();
        System.exit(0);
    }
}
