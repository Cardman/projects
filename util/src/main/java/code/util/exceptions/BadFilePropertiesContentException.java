package code.util.exceptions;

public class BadFilePropertiesContentException extends RuntimeException {

    private static final String SEP = "\nline:";
    private static final String SEP_END = "\n";

    private final int line;

    public BadFilePropertiesContentException(String _content, int _line) {
        super(_content+SEP+_line+SEP_END);
        line = _line;
    }

    public int getLine() {
        return line;
    }
}
