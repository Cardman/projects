package code.expressionlanguage.exec;

import code.expressionlanguage.exec.calls.util.CallingState;

public final class BreakPointOutputInfo {
    private CallingState callingStateSub;
    private CoreCheckedExecOperationNodeInfos operElt;
    private boolean stoppedBreakPoint;

    public CallingState getCallingStateSub() {
        return callingStateSub;
    }

    public void setCallingStateSub(CallingState _c) {
        this.callingStateSub = _c;
    }

    public CoreCheckedExecOperationNodeInfos getOperElt() {
        return operElt;
    }

    public void setOperElt(CoreCheckedExecOperationNodeInfos _o) {
        this.operElt = _o;
    }

    public boolean isStoppedBreakPoint() {
        return stoppedBreakPoint;
    }

    public void setStoppedBreakPoint(boolean _s) {
        this.stoppedBreakPoint = _s;
    }
}
