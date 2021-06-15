package uz.dev.apelsin.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String msg) {
        super(msg);
    }
}
