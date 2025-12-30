package db2.ufc;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Fighter")
public class Fighter extends Participant {

    @Column(name = "nickname")
    private String nickname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body_measurements_id", referencedColumnName = "id")
    private BodyMeasurements bodyMeasurements;

    @ManyToMany
    @JoinTable(
            name = "Fighter_WeightClass",
            joinColumns = @JoinColumn(name = "fighter_id"),
            inverseJoinColumns = @JoinColumn(name = "weightclass_id")
    )
    private Set<WeightClass> weightClasses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Fighter_Event",
            joinColumns = @JoinColumn(name = "fighter_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Fighter_GameRating",
            joinColumns = @JoinColumn(name = "fighter_id"),
            inverseJoinColumns = @JoinColumn(name = "gamerating_id")
    )
    private Set<GameRating> gameRatings = new HashSet<>();

    public Fighter() {
    }

    public Fighter(String firstname, String lastname, String nationality, LocalDate birthday, String nickname, BodyMeasurements bodyMeasurements) {
        super(firstname, lastname, nationality, birthday);
        this.nickname = nickname;
        this.bodyMeasurements = bodyMeasurements;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BodyMeasurements getBodyMeasurements() {
        return bodyMeasurements;
    }

    public void setBodyMeasurements(BodyMeasurements bodyMeasurements) {
        this.bodyMeasurements = bodyMeasurements;
    }

    public Set<WeightClass> getWeightClasses() {
        return weightClasses;
    }

    public void addWeightClass(WeightClass weightClass) {
        weightClasses.add(weightClass);
        weightClass.getFighters().add(this);
    }

    public void removeWeightClass(WeightClass weightClass) {
        weightClasses.remove(weightClass);
        weightClass.getFighters().remove(this);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.getFighters().add(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.getFighters().remove(this);
    }

    public Set<GameRating> getGameRatings() {
        return gameRatings;
    }

    public void addGameRating(GameRating gameRating) {
        gameRatings.add(gameRating);
        gameRating.getFighters().add(this);
    }

    public void removeGameRating(GameRating gameRating) {
        gameRatings.remove(gameRating);
        gameRating.getFighters().remove(this);
    }
}
