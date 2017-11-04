package code.expressionlanguage;

public final class ArgumentCall {

    private Argument argument;

    private Object invoking;

    private ArgumentCall() {
    }
    public static ArgumentCall newArgument(Argument _argument) {
        ArgumentCall arg_ = new ArgumentCall();
        arg_.argument = _argument;
        arg_.invoking = null;
        return arg_;
    }

    public static ArgumentCall newCall(Object _invoking) {
        ArgumentCall arg_ = new ArgumentCall();
        arg_.argument = null;
        arg_.invoking = _invoking;
        return arg_;
    }

    public Argument getArgument() {
        return argument;
    }

    public boolean isInitClass() {
        return invoking instanceof InitializatingClass;
    }

    public InitializatingClass getInitClass() {
        return (InitializatingClass) invoking;
    }

    public boolean isInvokeMethod() {
        return invoking instanceof InvokingMethod;
    }

    public InvokingMethod getInvokeMethod() {
        return (InvokingMethod) invoking;
    }

    public boolean isInvokeConstructor() {
        return invoking instanceof InvokingConstructor;
    }

    public InvokingConstructor getInvokeConstructor() {
        return (InvokingConstructor) invoking;
    }
}
