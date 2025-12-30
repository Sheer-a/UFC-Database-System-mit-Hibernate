package db2.ufc;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Sponsor")
public class Sponsor {


    @Column(name = "address")
    private String address;

    @Column(name = "oid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long oid;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "sponsors")
    private Set<Event> events;

    public Sponsor() {}

    public Sponsor(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.events = new HashSet<>();
    }

    public String getAddress() {
        return address;
    }

    public Long getOid() {
        return oid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.getSponsors().add(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.getSponsors().remove(this);
    }

    @Override
    public String toString() {
        return "Sponsor ID:\t\t" + oid +
                "\nName:\t\t\t" + name +
                "\nAddress:\t\t" + address +
                "\nPhone Number:\t" + phoneNumber;
    }

}
