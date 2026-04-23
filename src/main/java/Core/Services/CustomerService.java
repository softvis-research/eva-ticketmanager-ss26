package Core.Services;

import Core.Interfaces.CustomerServiceInterface;
import Core.Models.Customer;
import Core.Models.exceptions.CustomerException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class CustomerService implements CustomerServiceInterface {

    // E-Mail pattern following a simplified RFC 5322 definition: exactly one
    // '@' sign, non-empty local part, a domain and a top-level domain of at
    // least two characters.
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9_.+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$"
    );

    private static final int MINIMUM_AGE = 18;

    private final Map<UUID, Customer> customers = new ConcurrentHashMap<>();

    @Override
    public Customer createCustomer(String username, String email, LocalDate dateOfBirth) {
        validateUsername(username);
        validateEmail(email);
        validateDateOfBirth(dateOfBirth);

        UUID id = UUID.randomUUID();
        Customer customer = new Customer(id, username, email, dateOfBirth);
        // Store a defensive copy so external mutation of the returned Customer
        // cannot corrupt the service's internal state.
        customers.put(id, new Customer(customer));
        return customer;
    }

    @Override
    public Customer getCustomerById(UUID id) {
        if (!customers.containsKey(id)) {
            throw CustomerException.customerDoesNotExist();
        }
        return customers.get(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer == null || !customers.containsKey(customer.getId())) {
            throw CustomerException.customerDoesNotExist();
        }
        validateUsername(customer.getUsername());
        validateEmail(customer.getEmail());
        validateDateOfBirth(customer.getDateOfBirth());

        // Store a defensive copy so later mutations to the caller's Customer
        // reference do not silently change stored state.
        customers.put(customer.getId(), new Customer(customer));
    }

    @Override
    public void deleteCustomer(UUID id) {
        if (!customers.containsKey(id)) {
            throw CustomerException.customerDoesNotExist();
        }
        customers.remove(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public void deleteAllCustomers() {
        customers.clear();
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw CustomerException.usernameCannotBeEmpty();
        }
    }

    private void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw CustomerException.invalidEmail();
        }
    }

    private void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || !dateOfBirth.isBefore(LocalDate.now())) {
            throw CustomerException.invalidDateOfBirth();
        }
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < MINIMUM_AGE) {
            throw CustomerException.underageCustomer();
        }
    }
}
