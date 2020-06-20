package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;

public final class CustomFoundCast implements CallingState {

    private final String className;

    private final MethodId id;

    private final CustList<Argument> arguments;

    public CustomFoundCast(String _className, MethodId _id, CustList<Argument> _arguments) {
        className = _className;
        id = _id;
        arguments = _arguments;
    }

    public String getClassName() {
        return className;
    }

    public MethodId getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
