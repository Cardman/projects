package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.util.CustList;
import code.util.StringList;

public final class CustomFoundAnnotation {

    private final String className;

    private final StringList id;

    private final CustList<Argument> arguments;

    public CustomFoundAnnotation(String _className,
            StringList _id, CustList<Argument> _arguments) {
        className = _className;
        id = _id;
        arguments = _arguments;
    }

    public String getClassName() {
        return className;
    }

    public StringList getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
