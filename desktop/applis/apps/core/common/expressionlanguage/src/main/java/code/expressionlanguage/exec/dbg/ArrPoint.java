package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ArrPoint {
    public static final int AP=5;
    public static final int BPC_LENGTH = 0;
    private boolean enabled;
    private boolean length;
    private final BreakPointCondition resultLength;
    public ArrPoint(AbstractInterceptorStdCaller _i, AbsKeyPoint _key){
        resultLength = new BreakPointCondition(_i,_key,AP, BPC_LENGTH);
        setLength(true);
    }

    public void resetCount() {
        resultLength.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isLength() {
        return length;
    }

    public void setLength(boolean _l) {
        this.length = _l;
    }

    public BreakPointCondition getResultLength() {
        return resultLength;
    }


}
