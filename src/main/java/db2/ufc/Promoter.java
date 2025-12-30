package db2.ufc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Promoter")
public class Promoter extends Organizer {

    @Column(name = "contractTerm")
    private int contractTerm;

    public Promoter() {}

    public Promoter(String name, String address, String phoneNumber, int contractTerm) {
        super(name, address, phoneNumber);
        this.contractTerm = contractTerm;
    }

    public int getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(int contractTerm) {
        this.contractTerm = contractTerm;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nContract Term:\t" + contractTerm +
                "\nRole:\t\t" + this.getClass().getSimpleName();
    }
}
