package code.expressionlanguage.exec;

import code.expressionlanguage.exec.dbg.StackState;

public final class BreakPointInfo {
    private final StackState stackState = new StackState();
    private final BreakPointInputInfo breakPointInputInfo = new BreakPointInputInfo();
    private final BreakPointMiddleInfo breakPointMiddleInfo = new BreakPointMiddleInfo();
    private final BreakPointOutputInfo breakPointOutputInfo = new BreakPointOutputInfo();

    public StackState getStackState() {
        return stackState;
    }

    public BreakPointInputInfo getBreakPointInputInfo() {
        return breakPointInputInfo;
    }

    public BreakPointMiddleInfo getBreakPointMiddleInfo() {
        return breakPointMiddleInfo;
    }

    public BreakPointOutputInfo getBreakPointOutputInfo() {
        return breakPointOutputInfo;
    }
}
