package Core.Interfaces;

import Core.Models.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {
    Customer createCustomer(String username, String email, LocalDate dateOfBirth);
    Customer getCustomerById(UUID id);
    void updateCustomer(Customer customer);
    void deleteCustomer(UUID id);
    List<Customer> getAllCustomers();
    void deleteAllCustomers();
}
