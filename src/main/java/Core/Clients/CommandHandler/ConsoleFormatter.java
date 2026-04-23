package Core.Clients.CommandHandler;

import Core.Models.Customer;
import Core.Models.Event;

import java.time.format.DateTimeFormatter;

public class ConsoleFormatter {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    public static String formatCustomer(Customer customer) {
        return String.format(
                "%s - %s (%s)",
                customer.getUsername(),
                customer.getEmail(),
                customer.getDateOfBirth() != null
                        ? customer.getDateOfBirth()
                        : "No birthday"
        );
    }

    public static String formatEvent(Event event) {
        return String.format(
                "%s - %s (%s) - %d tickets",
                event.getName(),
                event.getLocation(),
                event.getTime().format(DATE_TIME_FORMATTER),
                event.getTicketsAvailable().get()
        );
    }
}
