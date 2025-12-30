package db2.ufc;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GameRating")
public class GameRating {

    @Column(name = "gr_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long grId;

    @Column(name = "description")
    private String description;

    @Column(name = "score")
    private int score;

    @OneToOne
    @JoinColumn(name = "g_id")
    private Game game;


    @ManyToMany
    @JoinTable(name = "GameRating_Fighter", joinColumns = @JoinColumn(name = "gr_id"), inverseJoinColumns = @JoinColumn(name = "fid"))
    private List<Fighter> fighters;

    // Existing methods...


    public GameRating() {
    }

    public GameRating(String description, int score) {
        this.description = description;
        this.score = score;
    }

    public Long getGrId() {
        return grId;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }

    public Game getGame() {
        return game;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(List<Fighter> fighters) {
        this.fighters = fighters;
    }


    @Override
    public String toString() {
        return "GameRating ID:\t" + grId + "\nDescription:\t" + description + "\nScore:\t\t\t" + score;
    }
}
