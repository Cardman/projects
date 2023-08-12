package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ExcPoint {
    private boolean enabled;
    private boolean thrown;
    private boolean caught;
    private boolean propagated;
    private final BreakPointCondition resultThrown;
    private final BreakPointCondition resultCaught;
    private final BreakPointCondition resultPropagated;
    public ExcPoint(AbstractInterceptorStdCaller _i){
        resultThrown = new BreakPointCondition(_i);
        resultCaught = new BreakPointCondition(_i);
        resultPropagated = new BreakPointCondition(_i);
    }

    public void resetCount() {
        resultThrown.getEnabled().set(true);
        resultThrown.setCount(0);
        resultCaught.getEnabled().set(true);
        resultCaught.setCount(0);
        resultPropagated.getEnabled().set(true);
        resultPropagated.setCount(0);
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isThrown() {
        return thrown;
    }

    public void setThrown(boolean _t) {
        this.thrown = _t;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean _c) {
        this.caught = _c;
    }

    public boolean isPropagated() {
        return propagated;
    }

    public void setPropagated(boolean _p) {
        this.propagated = _p;
    }

    public BreakPointCondition getResultThrown() {
        return resultThrown;
    }

    public BreakPointCondition getResultCaught() {
        return resultCaught;
    }

    public BreakPointCondition getResultPropagated() {
        return resultPropagated;
    }

}
