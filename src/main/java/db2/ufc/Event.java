package db2.ufc;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Event")
public class Event {

    @Column(name = "eid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long eid;

    @Column(name = "name")
    private String name;

    @Column(name = "venue")
    private String venue;

    @Column(name = "startDateTime")
    private LocalDateTime startDateTime;

    @Column(name = "endDateTime")
    private LocalDateTime endDateTime;

    @ManyToMany
    @JoinTable(name = "Event_Sponsor",
            joinColumns = @JoinColumn(name = "eid"),
            inverseJoinColumns = @JoinColumn(name = "sid"))
    private Set<Sponsor> sponsors;

    @OneToMany(mappedBy = "event")
    private List<Game> games;

    @ManyToMany(mappedBy = "events")
    private List<Fighter> fighters;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    public Event() {
        this.sponsors = new HashSet<>();
        this.games = new ArrayList<>();
        this.fighters = new ArrayList<>();
    }

    public Event(String name, String venue, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.name = name;
        this.venue = venue;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.sponsors = new HashSet<>();
        this.games = new ArrayList<>();
        this.fighters = new ArrayList<>();
    }

    public Long getEid() {
        return eid;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    public List<Game> getGames() {
        return games;
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public void addSponsor(Sponsor sponsor) {
        sponsors.add(sponsor);
    }

    public void removeSponsor(Sponsor sponsor) {
        sponsors.remove(sponsor);
    }

    public void addGame(Game game) {
        games.add(game);
        game.setEvent(this);
    }

    public void removeGame(Game game) {
        games.remove(game);
        game.setEvent(null);
    }

    public void setFighters(List<Fighter> fighters) {
        this.fighters = fighters;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public void addFighter(Fighter fighter) {
        fighters.add(fighter);
        fighter.getEvents().add(this);
    }

    public void removeFighter(Fighter fighter) {
        fighters.remove(fighter);
        fighter.getEvents().remove(this);
    }

    @Override
    public String toString() {
        return "Event ID:\t\t" + eid +
                "\nName:\t\t" + name +
                "\nVenue:\t\t" + venue +
                "\nStart Date/Time:\t" + startDateTime +
                "\nEnd Date/Time:\t" + endDateTime;
    }
}
