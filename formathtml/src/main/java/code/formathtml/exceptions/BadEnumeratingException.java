package code.formathtml.exceptions;
import code.util.ints.Listable;

public class BadEnumeratingException extends RuntimeException {

    private static final String SEP = "\n";

    public BadEnumeratingException(Listable<?> _li, String _message) {
        super(_li+SEP+_message);
    }
}
