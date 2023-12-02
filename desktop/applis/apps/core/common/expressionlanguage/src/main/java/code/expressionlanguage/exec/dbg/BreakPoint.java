package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class BreakPoint {
    public static final int BP= 0;
    public static final int BPC_STD = 0;
    public static final int BPC_STATIC = 1;
    public static final int BPC_INSTANCE = 2;
    private boolean enabled;
    private final BreakPointCondition resultStd;
    public BreakPoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key){
        resultStd = new BreakPointCondition(_i, _superKey, _key,BP, BPC_STD);
    }

    public void resetCount() {
        resultStd.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public BreakPointCondition getResultStd() {
        return resultStd;
    }

}