package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.dbg.BreakPointCondition;
import code.expressionlanguage.structs.Struct;

public final class BreakPointOutputInfo {
    private CallingState callingStateSub;
    private CoreCheckedExecOperationNodeInfos operElt;
    private BreakPointCondition bpc;
    private StopDbgEnum stoppedBreakPoint = StopDbgEnum.NONE;
    private Struct watchedObject;
    private Struct watchedTrace;
    private ContextEl subContext;

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

    public Struct getWatchedObject() {
        return watchedObject;
    }

    public void setWatchedObject(Struct _w) {
        this.watchedObject = _w;
    }

    public Struct getWatchedTrace() {
        return watchedTrace;
    }

    public void setWatchedTrace(Struct _w) {
        this.watchedTrace = _w;
    }

    public ContextEl getSubContext() {
        return subContext;
    }

    public void setSubContext(ContextEl _c) {
        this.subContext = _c;
    }
}
