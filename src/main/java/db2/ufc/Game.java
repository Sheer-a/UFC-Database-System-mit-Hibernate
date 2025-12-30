package db2.ufc;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;

@Entity
@Table(name = "Game")
public class Game {

    @Column(name = "g_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long gId;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private String date;

    @OneToOne(mappedBy = "game")
    private GameRating gameRating;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Game() {
    }

    public Game(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public Long getGId() {
        return gId;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public GameRating getGameRating() {
        return gameRating;
    }

    public Event getEvent() {
        return event;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setGameRating(GameRating gameRating) {
        this.gameRating = gameRating;
        gameRating.setGame(this);
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Game ID:\t\t" + gId +
                "\nDescription:\t" + description +
                "\nDate:\t\t\t" + date;
    }
}
