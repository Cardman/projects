package code.expressionlanguage.calls.util;
import code.expressionlanguage.Argument;
import code.util.CustList;
import code.util.StringMap;

public final class CustomFoundAnnotation implements CallingState {

    private final String className;

    private final StringMap<String> id;

    private final CustList<Argument> arguments;

    public CustomFoundAnnotation(String _className,
            StringMap<String> _id, CustList<Argument> _arguments) {
        className = _className;
        id = _id;
        arguments = _arguments;
    }

    public String getClassName() {
        return className;
    }

    public StringMap<String> getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
