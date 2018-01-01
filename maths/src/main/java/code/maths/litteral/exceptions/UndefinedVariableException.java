package code.maths.litteral.exceptions;

import code.util.StringList;

public class UndefinedVariableException extends RuntimeException {

    private static final String SEP = "\n";

    public UndefinedVariableException(String _message, String _where) {
        super(StringList.concat(_where,SEP,_message));
    }
}
