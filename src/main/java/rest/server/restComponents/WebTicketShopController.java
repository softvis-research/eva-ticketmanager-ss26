package rest.server.restComponents;

import java.util.List;
import java.util.NoSuchElementException;

import idGenerator.idService.IDService;
import rest.server.createRecords.CreateCustomerRequest;
import rest.server.createRecords.CreateEventRequest;
import rest.server.createRecords.CreateTicketRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import core.models.exceptions.TicketException;
import core.models.Customer;
import core.models.Event;
import core.models.Ticket;
import core.services.CustomerService;
import core.services.EventService;
import core.services.TicketService;


@RestController
public class WebTicketShopController {
    private final EventService eventService;
    private final CustomerService customerService;
    private final TicketService ticketService;


    public WebTicketShopController() {
        IDService idService = new IDService(10000L, 99999L);
        this.ticketService = new TicketService(idService);
        this.customerService = new CustomerService(ticketService, idService);
        this.eventService = new EventService(ticketService, idService);
        ticketService.setCustomerService(customerService);
        ticketService.setEventService(eventService);
    }

    @PostMapping("/event")
    public Event createEvent(@RequestBody CreateEventRequest request) {
        return eventService.createEvent(request.name(), request.location(), request.time(), request.ticketsAvailable());
    }

    @GetMapping("/event")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/event/{id}")
    public Event getEventById(@PathVariable long id) {
        try {
            return eventService.getEventById(id);
        } catch (Exception e){
            throw new NoSuchElementException();

        }
    }

    @PutMapping("/event")
    public void updateEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
    }

    @DeleteMapping("/event")
    public void deleteAllEvents() {
        eventService.deleteAllEvents();
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request.username(), request.email(), request.dateOfBirth());
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        try {
            return customerService.getCustomerById(id);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }


    @PutMapping("customer")
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
    }

    @DeleteMapping("/customer")
    public void deleteAllCustomers() {
        customerService.deleteAllCustomers();
    }

    // Ticket Operations
    @PostMapping("/ticket")
    public Ticket createTicket(@RequestBody CreateTicketRequest request) throws TicketException {
        return ticketService.createTicket(request.customerId(), request.eventId());
    }

    @GetMapping("/ticket")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable long id) throws TicketException {
        return ticketService.getTicketById(id);
    }

    @DeleteMapping("/ticket/{id}")
    public void deleteTicket(@PathVariable long id) {
        ticketService.deleteTicket(id);
    }

    @DeleteMapping("/ticket")
    public void deleteAllTickets() {
        ticketService.deleteAllTickets();
    }
}
