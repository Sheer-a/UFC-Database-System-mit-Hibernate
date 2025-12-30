package db2.ufc;

import jakarta.persistence.*;

@Entity
@Table(name = "BodyMeasurements")
public class BodyMeasurements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "arm_length")
    private Double armLength;

    @Column(name = "leg_length")
    private Double legLength;

    @OneToOne(mappedBy = "bodyMeasurements")
    private Fighter fighter;


    public BodyMeasurements(Double height, Double weight, Double armLength, Double legLength, Fighter fighter) {
        this.height = height;
        this.weight = weight;
        this.armLength = armLength;
        this.legLength = legLength;
        this.fighter = fighter;
    }

    public BodyMeasurements() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getArmLength() {
        return armLength;
    }

    public void setArmLength(Double armLength) {
        this.armLength = armLength;
    }

    public Double getLegLength() {
        return legLength;
    }

    public void setLegLength(Double legLength) {
        this.legLength = legLength;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }
}
