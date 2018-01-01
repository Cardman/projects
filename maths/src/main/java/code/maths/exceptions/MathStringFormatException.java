package code.maths.exceptions;

import code.util.StringList;

public class MathStringFormatException extends RuntimeException {

    private static final String SEPARATOR = "\n";

    public MathStringFormatException() {
    }

    public MathStringFormatException(String _message, int _index) {
        super(StringList.concat(Long.toString(_index),SEPARATOR,_message));
    }

}
