package code.maths.litteral.exceptions;

import code.util.StringList;

public class UndefinedFunctionException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedFunctionException(String _message, String _variable) {
        super(StringList.concat(_variable,SEP,_message));
    }
}
