package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class WatchPoint {
    private boolean enabled;
    private boolean read;
    private boolean write;
    private boolean compoundRead;
    private boolean compoundWrite;
    private final BreakPointCondition resultRead;
    private final BreakPointCondition resultWrite;
    private final BreakPointCondition resultCompoundRead;
    private final BreakPointCondition resultCompoundWrite;
    public WatchPoint(AbstractInterceptorStdCaller _i){
        resultRead = new BreakPointCondition(_i);
        resultWrite = new BreakPointCondition(_i);
        resultCompoundRead = new BreakPointCondition(_i);
        resultCompoundWrite = new BreakPointCondition(_i);
    }

    public void resetCount() {
        resultRead.getEnabled().set(true);
        resultRead.setCount(0);
        resultWrite.getEnabled().set(true);
        resultWrite.setCount(0);
        resultCompoundRead.getEnabled().set(true);
        resultCompoundRead.setCount(0);
        resultCompoundWrite.getEnabled().set(true);
        resultCompoundWrite.setCount(0);
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean _e) {
        this.read = _e;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean _e) {
        this.write = _e;
    }

    public boolean isCompoundRead() {
        return compoundRead;
    }

    public void setCompoundRead(boolean _e) {
        this.compoundRead = _e;
    }

    public boolean isCompoundWrite() {
        return compoundWrite;
    }

    public void setCompoundWrite(boolean _e) {
        this.compoundWrite = _e;
    }

    public BreakPointCondition getResultRead() {
        return resultRead;
    }

    public BreakPointCondition getResultWrite() {
        return resultWrite;
    }

    public BreakPointCondition getResultCompoundRead() {
        return resultCompoundRead;
    }

    public BreakPointCondition getResultCompoundWrite() {
        return resultCompoundWrite;
    }
}
