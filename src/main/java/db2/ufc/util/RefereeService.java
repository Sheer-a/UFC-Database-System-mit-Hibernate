package db2.ufc.util;

import db2.ufc.Referee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class RefereeService {

    private final EntityManager em;

    public RefereeService(EntityManager em) {
        this.em = em;
    }

    public List<Referee> getAllReferees() {
        TypedQuery<Referee> query = em.createQuery("SELECT r FROM Referee r", Referee.class);
        return query.getResultList();
    }

    @Transactional
    public void addReferee(Referee referee) {
        em.persist(referee);
        System.out.println("Referee added successfully!");
    }

    @Transactional
    public void deleteReferee(Long id) {
        Referee referee = em.find(Referee.class, id);
        if (referee != null) {
            em.remove(referee);
            System.out.println("Referee deleted successfully!");
        } else {
            System.out.println("Referee not found!");
        }
    }

    @Transactional
    public void updateReferee(Long id) {
        Referee referee = em.find(Referee.class, id);
        if (referee != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Referee's First Name: ");
            referee.setFirstname(scanner.next());
            System.out.print("Enter Referee's Last Name: ");
            referee.setLastname(scanner.next());
            System.out.print("Enter Referee's Nationality: ");
            referee.setNationality(scanner.next());
            System.out.print("Enter Referee's Birthday (YYYY-MM-DD): ");
            referee.setBirthday(LocalDate.parse(scanner.next()));
            System.out.print("Enter Referee's Certification Until (YYYY-MM-DD): ");
            referee.setCertificationUntil(LocalDate.parse(scanner.next()));
            System.out.print("Enter Referee's Valuation Rank: ");
            em.merge(referee);
            System.out.println("Referee updated successfully!");
        } else {
            System.out.println("Referee not found!");
        }
    }
}
