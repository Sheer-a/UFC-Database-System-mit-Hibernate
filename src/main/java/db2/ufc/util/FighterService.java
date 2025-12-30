package db2.ufc.util;

import db2.ufc.BodyMeasurements;
import db2.ufc.Fighter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class FighterService {

    private final EntityManager em;

    public FighterService(EntityManager em) {
        this.em = em;
    }


    public List<Fighter> getAllFighters() {
        TypedQuery<Fighter> query = em.createQuery("SELECT f FROM Fighter f", Fighter.class);
        return query.getResultList();
    }


    @Transactional
    public void addFighter(Fighter fighter, BodyMeasurements bodyMeasurements) {
        em.persist(bodyMeasurements);
        em.persist(fighter);
    }

    @Transactional
    public void deleteFighter(Long id) {
        Fighter fighter = em.find(Fighter.class, id);
        if (fighter != null) {
            em.remove(fighter);
            System.out.println("Fighter deleted successfully!");
        } else {
            System.out.println("Fighter not found!");
        }
    }

    @Transactional
    public void updateFighter(Long id) {
        Fighter fighter = em.find(Fighter.class, id);
        if (fighter != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Fighter's First Name: ");
            String firstname = scanner.next();
            System.out.print("Enter Fighter's Last Name: ");
            String lastname = scanner.next();
            System.out.print("Enter Fighter's Nationality: ");
            String nationality = scanner.next();
            System.out.print("Enter Fighter's Birthday (YYYY-MM-DD): ");
            LocalDate birthday = LocalDate.parse(scanner.next());
            fighter.setFirstname(firstname);
            fighter.setLastname(lastname);
            fighter.setNationality(nationality);
            fighter.setBirthday(birthday);
            em.merge(fighter);
            System.out.println("Fighter updated successfully!");
        } else {
            System.out.println("Fighter not found!");
        }
    }
}
