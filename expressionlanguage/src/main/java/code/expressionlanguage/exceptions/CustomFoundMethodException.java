package code.expressionlanguage.exceptions;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;

public class CustomFoundMethodException extends RuntimeException {

    private final Argument gl;

    private final String className;

    private final FctConstraints id;

    private final CustList<Argument> arguments;

    public CustomFoundMethodException(Argument _gl,String _className, FctConstraints _id, CustList<Argument> _arguments) {
        gl = _gl;
        className = _className;
        id = _id;
        arguments = _arguments;
    }

    public Argument getGl() {
        return gl;
    }

    public String getClassName() {
        return className;
    }

    public FctConstraints getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
