package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class MethodPoint {
    public static final int BPC_ENTRY = 0;
    public static final int BPC_EXIT = 1;
    private boolean enabled;
    private final BreakPointCondition resultEntry;
    private final BreakPointCondition resultExit;
    private boolean entry;
    private boolean exit;
    public MethodPoint(AbstractInterceptorStdCaller _i, AbsKeyPoint _key, int _c){
        resultEntry = new BreakPointCondition(_i,_key,_c, BPC_ENTRY);
        resultExit = new BreakPointCondition(_i,_key,_c, BPC_EXIT);
        setEntry(true);
        setExit(true);
    }

    public BreakPointCondition result(boolean _exit) {
        if (_exit) {
            return resultExit;
        }
        return resultEntry;
    }
    public void resetCount() {
        resultEntry.resetCount();
        resultExit.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isEntry() {
        return entry;
    }

    public void setEntry(boolean _e) {
        this.entry = _e;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean _e) {
        this.exit = _e;
    }

    public BreakPointCondition getResultEntry() {
        return resultEntry;
    }

    public BreakPointCondition getResultExit() {
        return resultExit;
    }
}