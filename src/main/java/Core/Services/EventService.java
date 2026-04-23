package Core.Services;

import Core.Models.exceptions.EventException;
import Core.Interfaces.EventServiceInterface;
import java.time.LocalDateTime;
import java.util.*;
import Core.Models.Event;

public class EventService implements EventServiceInterface {

    ArrayList<Event> EventList = new ArrayList<>();

    public Event createEvent(String name, String location, LocalDateTime time, int ticketsAvailable) throws EventException {

        Event event = new Event(
                UUID.randomUUID(),
                name,
                location,
                time,
                ticketsAvailable
        );
        EventList.add(event);

        return new Event(event.getId(),
                name,
                location,
                time,
                ticketsAvailable);
    }

    @Override
    public Event getEventById(UUID id) {


        for (Event event : EventList) {
            if (event.getId().equals(id)) {
                return new Event(
                        event.getId(),
                        event.getName(),
                        event.getLocation(),
                        event.getTime(),
                        event.getTicketsAvailable().get()
                );
            }
        }

        throw new EventException("Event with ID " + id + " not found");
    }

    @Override
    public void updateEvent(Event event) throws EventException {

        UUID uuid = event.getId();

        for (Event e : EventList) {
            if (e.getId().equals(uuid)) {
                if(e.getTicketsAvailable().get() > event.getTicketsAvailable().get()){
                    throw new EventException("Must not reduce available ticket contingent of existing event.");
                }
                e.setName(event.getName());
                e.setLocation(event.getLocation());
                e.setTime(event.getTime());
                e.getTicketsAvailable().set(event.getTicketsAvailable().get());
                validateUpdatedEvent(e);
                return;
            }
        }
        throw new EventException("Event does not exist");

    }

    private void validateUpdatedEvent(Event event){

        if(event.getTime().isBefore(LocalDateTime.now())){
            throw new EventException("DateTime of Event can't be set into the past");
        }
        if(event.getTicketsAvailable().get() < 0){
            throw new EventException("Tickets available cannot be negative");
        }

    }


    @Override
    public void deleteEvent(UUID id) {

        for (Event event : EventList) {
            if (event.getId().equals(id)) {
                EventList.remove(event);
                return;
            }
        }
        throw new EventException("Event with ID " + id + " not found");

    }

    @Override
    public List<Event> getAllEvents() {

        return EventList;
    }

    @Override
    public void deleteAllEvents() {

        EventList.clear();

    }


}
