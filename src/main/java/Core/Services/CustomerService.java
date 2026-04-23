package Core.Services;

import Core.Models.exceptions.CustomerException;
import Core.Interfaces.CustomerServiceInterface;
import java.time.LocalDate;
import java.util.*;
import Core.Models.Customer;

public class CustomerService implements CustomerServiceInterface {

    private final Map<UUID, Customer> customersById = new HashMap<>();

    public Customer createCustomer(
        String username,
        String email,
        LocalDate dateOfBirth
    ) throws IllegalArgumentException {

        //TODO

        return null;
    }

    public Customer getCustomerById(UUID id) throws CustomerException {

        //TODO

        return null;
    }


    public void updateCustomer(Customer customer) throws CustomerException {

        //TODO

    }



    public void deleteCustomer(UUID id) throws IllegalArgumentException {

        //TODO

    }

    public List<Customer> getAllCustomers() {

        //TODO

        return null;
    }

    public void deleteAllCustomers() {

        //TODO

    }

}
