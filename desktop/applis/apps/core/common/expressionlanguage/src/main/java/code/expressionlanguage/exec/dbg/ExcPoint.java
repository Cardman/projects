package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ExcPoint {
    public static final int EP=1;
    public static final int BPC_THROWN = 0;
    public static final int BPC_CAUGHT = 1;
    public static final int BPC_PROPAGATED = 2;
    private boolean enabled;
    private boolean thrown;
    private boolean caught;
    private boolean propagated;
    private final BreakPointCondition resultThrown;
    private final BreakPointCondition resultCaught;
    private final BreakPointCondition resultPropagated;
    public ExcPoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key){
        resultThrown = new BreakPointCondition(_i, _superKey, _key,EP, BPC_THROWN);
        resultCaught = new BreakPointCondition(_i, _superKey, _key,EP, BPC_CAUGHT);
        resultPropagated = new BreakPointCondition(_i, _superKey, _key,EP, BPC_PROPAGATED);
        setThrown(true);
        setCaught(true);
        setPropagated(true);
    }

    public void resetCount() {
        resultThrown.resetCount();
        resultCaught.resetCount();
        resultPropagated.resetCount();
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

    public BreakPointCondition result(int _m) {
        if (!isEnabled()) {
            return null;
        }
        return resultBpc(_m).stopBpc(resultEn(_m));
    }

    public BreakPointCondition resultBpc(int _m) {
        if (_m == BPC_THROWN) {
            return getResultThrown();
        }
        if (_m == BPC_CAUGHT) {
            return getResultCaught();
        }
        return getResultPropagated();
    }

    public boolean resultEn(int _m) {
        if (_m == BPC_THROWN) {
            return isThrown();
        }
        if (_m == BPC_CAUGHT) {
            return isCaught();
        }
        return isPropagated();
    }
}
