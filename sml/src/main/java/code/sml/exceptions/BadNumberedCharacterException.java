package code.sml.exceptions;

public class BadNumberedCharacterException extends RuntimeException {

    private static final String SEP_INDEX = "\nindex:";
    private static final String SEP_VALUE = "\nvalue:";
    private static final String SEP_END = "\n";

    public BadNumberedCharacterException(String _string, int _index, String _value) {
        super(_string+SEP_INDEX+_index+SEP_VALUE+_value+SEP_END);
    }
}
