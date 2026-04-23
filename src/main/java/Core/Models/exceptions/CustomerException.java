package Core.Models.exceptions;

public class CustomerException extends RuntimeException {

    public static final String customerDoesNotExist = "Customer does not exist";
    public static final String underageCustomer = "User has to be 18 years old";
    public static final String invalidEmail = "Invalid email";
    public static final String invalidDateOfBirth = "Date of birth must be a valid date in the past";
    public static final String usernameCannotBeEmpty = "Username cannot be null or empty";

    public CustomerException(String message) {
        super(message);
    }

    public static CustomerException customerDoesNotExist() {
        return new CustomerException(customerDoesNotExist);
    }

    public static CustomerException underageCustomer() {
        return new CustomerException(underageCustomer);
    }

    public static CustomerException invalidEmail() {
        return new CustomerException(invalidEmail);
    }

    public static CustomerException invalidDateOfBirth() {
        return new CustomerException(invalidDateOfBirth);
    }

    public static CustomerException usernameCannotBeEmpty() {
        return new CustomerException(usernameCannotBeEmpty);
    }
}
