package code.expressionlanguage.exec.stacks;


import code.expressionlanguage.exec.blocks.ExecBlock;

public final class AbruptCallingFinally {
    private final ExecBlock callingFinally;

    public AbruptCallingFinally(ExecBlock _callingFinally) {
        callingFinally = _callingFinally;
    }

    public ExecBlock getCallingFinally() {
        return callingFinally;
    }
}
