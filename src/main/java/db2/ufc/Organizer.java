package db2.ufc;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Organizer")
public class Organizer {


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

    @OneToMany(mappedBy = "organizer")
    private Set<Event> events;

    public Organizer() {}

    public Organizer(String name, String address, String phoneNumber) {
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
        event.setOrganizer(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setOrganizer(null);
    }

    @Override
    public String toString() {
        return "Organizer ID:\t" + oid +
                "\nName:\t\t" + name +
                "\nAddress:\t" + address +
                "\nPhone Number:\t" + phoneNumber;
    }

}
