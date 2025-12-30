package db2.ufc;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Participant")
public abstract class Participant {

    public enum FightingTechnique {
        BOXING,
        MUAY_THAI,
        KARATE,
        JUDO,
        WRESTLING,
        RINGEN,
        KICKBOXING,
        BRAZILIAN_JIU_JITSU
    }


    @Column(name = "pid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long pid;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "birthday")
    private LocalDate birthday;

    @ElementCollection
    private Set<FightingTechnique> fightingTechniques;

    public Participant() {
    }

    Participant(String firstname, String lastname, String nationality, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationality = nationality;
        this.birthday = birthday;
        this.fightingTechniques = EnumSet.noneOf(FightingTechnique.class);
    }

    public long getPid() {
        return pid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Set<FightingTechnique> getFightingTechniques() {
        return fightingTechniques;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void removeFightingTechnique(FightingTechnique ft) {
        fightingTechniques.remove(ft);
    }

    public void addFightingTechnique(FightingTechnique ft) {
        fightingTechniques.add(ft);
    }

    public void addFightingTechnique(FightingTechnique... fts) {
        for (FightingTechnique ft : fts)
            fightingTechniques.add(ft);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(pid).append("\n");
        sb.append("Name: ").append(firstname).append(" ").append(lastname).append("\n");
        sb.append("Nationality: ").append(nationality).append("\n");
        sb.append("Birthday: ").append(birthday).append("\n");
        return sb.toString();
    }

}
