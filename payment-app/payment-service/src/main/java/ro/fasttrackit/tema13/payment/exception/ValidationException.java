package ro.fasttrackit.tema13.payment.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}
