package Core.Services;

import Core.Models.exceptions.EventException;
import Core.Interfaces.EventServiceInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import Core.Models.Event;

public class EventService implements EventServiceInterface {

    private final Map<UUID, Event> eventsById = new ConcurrentHashMap<>();

    public Event createEvent(
        String name,
        String location,
        LocalDateTime time,
        int ticketsAvailable
    ) throws EventException {

        Event event = new Event(
            UUID.randomUUID(),
            name,
            location,
            time,
            ticketsAvailable
        );

        saveEvent(event);
        return event;
    }

    @Override
    public Event getEventById(UUID id) {
        try{
            if(!eventsById.containsKey(id)){
                throw EventException.eventDoesNotExist();
            }
          } catch (NullPointerException e){
                throw EventException.eventDoesNotExist();
        }
        return clone(eventsById.get(id));
    }

    @Override
    public void updateEvent(Event event) throws EventException {
        validateUpdatedEvent(event);
        saveEvent(event);
    }

    private void validateUpdatedEvent(Event event){
        Event eventBeforeUpdate = getEventById(event.getId());
        if (event.getTicketsAvailable().get() < eventBeforeUpdate.getTicketsAvailable().get()) {
            throw EventException.shouldNotReduceAvailableTicketsWithUpdate();
        }

        if (!event.getTime().isAfter(LocalDateTime.now())) {
            throw EventException.cantSetEventTimeIntoPast();
        }
    }


    @Override
    public void deleteEvent(UUID id) {
        eventsById.remove(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(eventsById.values());
    }

    @Override
    public void deleteAllEvents() {
        eventsById.clear();
    }

    private void saveEvent(Event event) throws EventException{
        validateEvent(event);
        eventsById.put(event.getId(), clone(event));
    }

    private void validateEvent(Event event){
        if (event.getTicketsAvailable().get() < 0) {
            throw EventException.negativeTicketsAvailable();
        }
    }

    private Event clone(Event event){
        Event clonedEvent = new Event(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getTime(),
                event.getTicketsAvailable().get()
        );
        return clonedEvent;
    }
}
