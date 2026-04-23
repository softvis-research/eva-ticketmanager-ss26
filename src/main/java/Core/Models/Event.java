package Core.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private final UUID id;
    private String name;
    private String location;
    private LocalDateTime time;
    private AtomicInteger ticketsAvailable;

    public Event(
        UUID id,
        String name,
        String location,
        LocalDateTime time,
        int ticketsAvailable
    ) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.time = time;
        this.ticketsAvailable = new AtomicInteger(ticketsAvailable);
    }

    /**
     * Copy constructor. Produces a deep-enough copy so that mutations to the
     * source do not affect the copy (and vice versa). Used by EventService to
     * keep a defensive snapshot of the stored state, so callers can freely
     * mutate the Event they received without corrupting the service's view.
     */
    public Event(Event other) {
        this.id = other.id;
        this.name = other.name;
        this.location = other.location;
        this.time = other.time;
        this.ticketsAvailable = new AtomicInteger(other.ticketsAvailable.get());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public AtomicInteger getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = new AtomicInteger(ticketsAvailable);
    }

    public boolean hasAvailableTickets() {
        return ticketsAvailable.get() > 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, location, time, ticketsAvailable);
    }

    @Override
    public boolean equals(Object objectToCompare){
        if (this == objectToCompare) return true;
        if(objectToCompare == null || getClass() != objectToCompare.getClass()) return false;
        Event eventToCompare = (Event) objectToCompare;
        return eventToCompare.getId().equals(this.getId()) &&
                eventToCompare.getName().equals(this.name) &&
                eventToCompare.getLocation().equals(this.location) &&
                eventToCompare.getTime().equals(this.time) &&
                (eventToCompare.getTicketsAvailable().get() == this.ticketsAvailable.get());
    }

}
