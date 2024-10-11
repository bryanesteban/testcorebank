package ec.com.corebank.banquito.ErrorManagment;


public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}