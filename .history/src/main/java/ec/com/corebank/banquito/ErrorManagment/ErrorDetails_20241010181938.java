package ec.com.corebank.banquito.ErrorManagment;

import java.util.Date;

public class ErrorDetails {

    private int status;
    private String message;
    private String details;

    public ErrorDetails(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    // Getters y Setters

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}