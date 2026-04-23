package Core.Interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import Core.Models.Customer;
import Core.Models.Event;

public interface TicketShopInterface {
    List<Event> getAllEvents();
    Event createEvent(String name, String location, LocalDateTime time, int ticketsAvailable);
    Event getEventById(UUID id);
    void updateEvent(Event event);
    void deleteEvent(UUID id);
    void deleteAllEvents();

    // Customer operations
    List<Customer> getAllCustomers();
    Customer createCustomer(String username, String email, LocalDate dateOfBirth);
    Customer getCustomerById(UUID id);
    void updateCustomer(Customer customer);
    void deleteCustomer(UUID id);
    void deleteAllCustomers();
}
