package code.expressionlanguage.exec;

public final class StackCallReturnValue {
    private final StackCall stack;
    private final ArgumentWrapper retValue;

    public StackCallReturnValue(StackCall _s, ArgumentWrapper _r) {
        this.stack = _s;
        this.retValue = _r;
    }

    public StackCall getStack() {
        return stack;
    }

    public ArgumentWrapper getRetValue() {
        return retValue;
    }
}
