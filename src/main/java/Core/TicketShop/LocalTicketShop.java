package Core.TicketShop;

import Core.Interfaces.TicketShopInterface;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import Core.Models.Customer;
import Core.Models.Event;
import Core.Services.CustomerService;
import Core.Services.EventService;

public class LocalTicketShop implements TicketShopInterface {

    private final EventService eventService;
    private final CustomerService customerService;

    public LocalTicketShop() {
        this.eventService = new EventService();
        this.customerService = new CustomerService();
    }

    // Event operations
    @Override
    public Event createEvent(
            String name,
            String location,
            LocalDateTime time,
            int tickets
    ) {
        return eventService.createEvent(name, location, time, tickets);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @Override
    public Event getEventById(UUID id) {
        return eventService.getEventById(id);
    }

    @Override
    public void updateEvent(Event event) {
        eventService.updateEvent(event);
    }

    @Override
    public void deleteEvent(UUID id) {
        eventService.deleteEvent(id);
    }

    @Override
    public void deleteAllEvents() {
        eventService.deleteAllEvents();
    }

    // Customer operations
    @Override
    public Customer createCustomer(String username, String email, LocalDate dateOfBirth) {
        return customerService.createCustomer(username, email, dateOfBirth);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerService.getCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerService.deleteCustomer(id);
    }

    @Override
    public void deleteAllCustomers() {
        customerService.deleteAllCustomers();
    }

}
