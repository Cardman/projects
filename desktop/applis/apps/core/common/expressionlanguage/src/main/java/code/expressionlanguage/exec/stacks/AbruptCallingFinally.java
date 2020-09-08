package code.expressionlanguage.exec.stacks;


public class AbruptCallingFinally {
    private final Object callingFinally;

    public AbruptCallingFinally(Object _callingFinally) {
        callingFinally = _callingFinally;
    }

    public Object getCallingFinally() {
        return callingFinally;
    }
}
