package db2.ufc;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Referee")
public class Referee extends Participant {

    @Column(name = "certificationUntil")
    private LocalDate certificationUntil;

    @Column(name = "valuationRank")
    private int valuationRank;

    public Referee() {}

    public Referee(String firstname, String lastname, String nationality, LocalDate birthday, LocalDate certificationUntil, int valuationRank) {
        super(firstname, lastname, nationality, birthday);
        this.certificationUntil = certificationUntil;
        this.valuationRank = valuationRank;
    }

    public LocalDate getCertificationUntil() {
        return certificationUntil;
    }

    public int getValuationRank() {
        return valuationRank;
    }

    public void setCertificationUntil(LocalDate certificationUntil) {
        this.certificationUntil = certificationUntil;
    }

    public void setValuationRank(int valuationRank) {
        this.valuationRank = valuationRank;
    }

    @Override
    public String toString() {
        String result = super.toString();
        result += "\nCertification End:\t" + certificationUntil
                + "\nValuation Rank:\t\t" + valuationRank
                + "\nRole:\t\t\t" + this.getClass().getCanonicalName();
        return result;
    }

}
