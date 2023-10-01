package code.expressionlanguage.exec;

import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.dbg.BreakPointCondition;

public final class BreakPointOutputInfo {
    private CallingState callingStateSub;
    private CheckedMethodInfos operElt;
    private BreakPointCondition bpc;
    private StopDbgEnum stoppedBreakPoint = StopDbgEnum.NONE;
    private final WatchResults watchResults = new WatchResults();

    public CallingState getCallingStateSub() {
        return callingStateSub;
    }

    public void setCallingStateSub(CallingState _c) {
        this.callingStateSub = _c;
    }

    public CheckedMethodInfos getOperElt() {
        return operElt;
    }

    public void setOperElt(CheckedMethodInfos _o) {
        this.operElt = _o;
    }

    public BreakPointCondition getBpc() {
        return bpc;
    }

    public void setBpc(BreakPointCondition _b) {
        this.bpc = _b;
    }

    public boolean isStoppedBreakPoint() {
        return getStoppedBreakPoint() != StopDbgEnum.NONE;
    }

    public StopDbgEnum getStoppedBreakPoint() {
        return stoppedBreakPoint;
    }

    public void setStoppedBreakPoint(StopDbgEnum _s) {
        this.stoppedBreakPoint = _s;
    }

    public WatchResults getWatchResults() {
        return watchResults;
    }

}
