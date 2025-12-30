package db2.ufc;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "WeightClass")
public class WeightClass {

    @Column(name = "minWeight")
    private double minWeight;

    @Column(name = "maxWeight")
    private double maxWeight;

    @Column(name = "wcid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long wcid;

    @Column(name = "expression")
    private String expression;

    @ManyToMany(mappedBy = "weightClasses")
    private Set<Fighter> fighters;

    public WeightClass() {}

    public WeightClass(double minWeight, double maxWeight, String expression) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.expression = expression;
        this.fighters = new HashSet<>();
    }

    public double getMinWeight() {
        return minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public String getExpression() {
        return expression;
    }

    public Set<Fighter> getFighters() {
        return fighters;
    }

    public void setMinWeight(double minWeight) {
        this.minWeight = minWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void addFighter(Fighter fighter) {
        fighters.add(fighter);
        fighter.getWeightClasses().add(this);
    }

    public void removeFighter(Fighter fighter) {
        fighters.remove(fighter);
        fighter.getWeightClasses().remove(this);
    }

    @Override
    public String toString() {
        return "WeightClass ID:\t" + wcid +
                "\nWeight Range:\t" + minWeight + " kg - " + maxWeight + " kg" +
                "\nExpression:\t" + expression;
    }
}
