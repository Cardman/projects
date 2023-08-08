package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class MethodPoint {
    private boolean enabled;
    private final BreakPointCondition resultEntry;
    private final BreakPointCondition resultExit;
    private boolean entry;
    private boolean exit;
    public MethodPoint(AbstractInterceptorStdCaller _i){
        resultEntry = new BreakPointCondition(_i);
        resultExit = new BreakPointCondition(_i);
    }

    public void resetCount() {
        resultEntry.getEnabled().set(true);
        resultEntry.setCount(0);
        resultExit.getEnabled().set(true);
        resultExit.setCount(0);
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