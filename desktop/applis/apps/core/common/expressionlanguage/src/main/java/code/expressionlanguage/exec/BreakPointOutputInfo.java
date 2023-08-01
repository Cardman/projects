package code.expressionlanguage.exec;

import code.expressionlanguage.exec.calls.util.CallingState;

public final class BreakPointOutputInfo {
    private CallingState callingStateSub;
    private CoreCheckedExecOperationNodeInfos operElt;
    private CheckedMethodInfos checkedMethodInfos;
    private StopDbgEnum stoppedBreakPoint = StopDbgEnum.NONE;

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

    public CheckedMethodInfos getCheckedMethodInfos() {
        return checkedMethodInfos;
    }

    public void setCheckedMethodInfos(CheckedMethodInfos _c) {
        this.checkedMethodInfos = _c;
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
}
