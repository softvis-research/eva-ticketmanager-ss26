package Core.Services;

import Core.Models.exceptions.EventException;
import Core.Interfaces.EventServiceInterface;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import Core.Models.Event;

public class EventService implements EventServiceInterface {

    //TODO variable to store events
    private final Map<UUID, Event> events = new ConcurrentHashMap<>();

    public Event createEvent(String name, String location, LocalDateTime time, int ticketsAvailable) throws EventException {
        UUID id = UUID.randomUUID();
        //validate inputs
        Event event = new Event(id, name, location, time, ticketsAvailable);
        // Store a defensive copy so external mutation of the returned Event
        // cannot corrupt the service's internal state.
        events.put(id, new Event(event));
        return event;
    }

    @Override
    public Event getEventById(UUID id) {

        if (!events.containsKey(id)) {
            throw new EventException("Event with ID " + id + " does not exist.");
        }
        return events.get(id);
    }

    @Override
    public void updateEvent(Event event) throws EventException {

        if (!events.containsKey(event.getId())) {
            throw EventException.eventDoesNotExist();
        }
        validateUpdatedEvent(event);
        // Store a defensive copy so later mutations to the caller's Event
        // reference do not silently change stored state.
        events.put(event.getId(), new Event(event));
    }

    private void validateUpdatedEvent(Event event){

        if (event.getName() == null || event.getName().isEmpty()) {
            throw new EventException("Event name cannot be null or empty.");
        }
        if (event.getLocation() == null || event.getLocation().isEmpty()) {
            throw new EventException("Event location cannot be null or empty.");
        }
        if (event.getTime() == null || event.getTime().isBefore(LocalDateTime.now())) {
            throw EventException.cantSetEventTimeIntoPast();
        }
        if (event.getTicketsAvailable().get() < 0) {
            throw new EventException("Tickets available cannot be negative.");
        }
        // cannot reduce tickets
        if (event.getTicketsAvailable().get() < events.get(event.getId()).getTicketsAvailable().get()) {
            throw EventException.shouldNotReduceAvailableTicketsWithUpdate();
        }
        
        return;
    }


    @Override
    public void deleteEvent(UUID id) {

        if (!events.containsKey(id)) {
            throw new EventException("Event with ID " + id + " does not exist.");
        }
        events.remove(id);
    }

    @Override
    public List<Event> getAllEvents() {

        return new ArrayList<>(events.values());
    }

    @Override
    public void deleteAllEvents() {

        events.clear();
    }


}
