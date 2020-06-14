package code.expressionlanguage.exec.stacks;

import code.expressionlanguage.methods.CallingFinally;

public class AbruptCallingFinally {
    private final CallingFinally callingFinally;

    public AbruptCallingFinally(CallingFinally _callingFinally) {
        callingFinally = _callingFinally;
    }

    public CallingFinally getCallingFinally() {
        return callingFinally;
    }
}
