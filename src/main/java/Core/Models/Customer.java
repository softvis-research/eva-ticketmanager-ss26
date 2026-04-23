package Core.Models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private String username;
    private String email;
    private LocalDate dateOfBirth;

    public Customer(UUID id, String username, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Copy constructor. Produces a copy of the given Customer so the
     * CustomerService can keep a defensive snapshot of the stored state.
     * Callers can freely mutate the Customer they received without
     * corrupting the service's view.
     */
    public Customer(Customer other) {
        this.id = other.id;
        this.username = other.username;
        this.email = other.email;
        this.dateOfBirth = other.dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, dateOfBirth);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (this == objectToCompare) return true;
        if (objectToCompare == null || getClass() != objectToCompare.getClass()) return false;
        Customer customerToCompare = (Customer) objectToCompare;
        return customerToCompare.getId().equals(this.id) &&
                Objects.equals(customerToCompare.getUsername(), this.username) &&
                Objects.equals(customerToCompare.getEmail(), this.email) &&
                Objects.equals(customerToCompare.getDateOfBirth(), this.dateOfBirth);
    }
}
