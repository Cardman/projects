package code.expressionlanguage.exec;

import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.dbg.BreakPointCondition;

public final class BreakPointOutputInfo {
    private CallingState callingStateSub;
    private CoreCheckedExecOperationNodeInfos operElt;
    private BreakPointCondition bpc;
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

}
