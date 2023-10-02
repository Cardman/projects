package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class WatchPoint {
    public static final int WP=2;
    public static final int BPC_READ = 0;
    public static final int BPC_WRITE = 1;
    public static final int BPC_COMPOUND_READ = 2;
    public static final int BPC_COMPOUND_WRITE = 3;
    public static final int BPC_COMPOUND_WRITE_ERR = 4;
    private boolean enabled;
    private boolean read;
    private boolean write;
    private boolean compoundRead;
    private boolean compoundWrite;
    private boolean compoundWriteErr;
    private final BreakPointCondition resultRead;
    private final BreakPointCondition resultWrite;
    private final BreakPointCondition resultCompoundRead;
    private final BreakPointCondition resultCompoundWrite;
    private final BreakPointCondition resultCompoundWriteErr;
    public WatchPoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key){
        resultRead = new BreakPointCondition(_i, _superKey, _key,WP, BPC_READ);
        resultWrite = new BreakPointCondition(_i, _superKey, _key,WP, BPC_WRITE);
        resultCompoundRead = new BreakPointCondition(_i, _superKey, _key,WP, BPC_COMPOUND_READ);
        resultCompoundWrite = new BreakPointCondition(_i, _superKey, _key,WP, BPC_COMPOUND_WRITE);
        resultCompoundWriteErr = new BreakPointCondition(_i, _superKey, _key,WP, BPC_COMPOUND_WRITE_ERR);
        setRead(true);
        setWrite(true);
        setCompoundRead(true);
        setCompoundWrite(true);
        setCompoundWriteErr(true);
    }

    public void resetCount() {
        resultRead.resetCount();
        resultWrite.resetCount();
        resultCompoundRead.resetCount();
        resultCompoundWrite.resetCount();
        resultCompoundWriteErr.resetCount();
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

    public boolean isCompoundWriteErr() {
        return compoundWriteErr;
    }

    public void setCompoundWriteErr(boolean _c) {
        this.compoundWriteErr = _c;
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

    public BreakPointCondition getResultCompoundWriteErr() {
        return resultCompoundWriteErr;
    }

    public BreakPointCondition result(int _m) {
        if (!isEnabled()) {
            return null;
        }
        if (_m == WatchPoint.BPC_READ) {
            return getResultRead().stopBpc(isRead());
        }
        if (_m == WatchPoint.BPC_WRITE) {
            return getResultWrite().stopBpc(isWrite());
        }
        if (_m == WatchPoint.BPC_COMPOUND_READ) {
            return getResultCompoundRead().stopBpc(isCompoundRead());
        }
        if (_m == WatchPoint.BPC_COMPOUND_WRITE) {
            return getResultCompoundWrite().stopBpc(isCompoundWrite());
        }
        return getResultCompoundWriteErr().stopBpc(isCompoundWriteErr());
    }
}
