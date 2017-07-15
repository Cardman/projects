package code.formathtml.exceptions;
import code.util.exceptions.RuntimeClassNotFoundException;

public class SuperClassNotFoundException extends RuntimeClassNotFoundException {

    private static final String CONCAT = " - ";
    private static final String CONTEXT = "\n";

    public SuperClassNotFoundException() {
    }

    public SuperClassNotFoundException(String _className, String _typedName) {
        super(_className + CONCAT + _typedName);
    }

    public SuperClassNotFoundException(String _context, String _className, String _typedName) {
        super(_className+CONCAT+_typedName + CONTEXT + _context);
    }
}
