package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class OperNatPoint implements AbsOperNatPoint{
    public static final int OP= 7;
    public static final int BPC_SIMPLE = 0;
    public static final int BPC_COMPOUND = 1;
    private boolean enabled;
    private boolean simple = true;
    private final BreakPointCondition resultSimple;
    public OperNatPoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key){
        resultSimple = new BreakPointCondition(_i, _superKey, _key,OP, BPC_SIMPLE);
    }

    public void resetCount() {
        resultSimple.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isSimple() {
        return simple;
    }

    public void setSimple(boolean _s) {
        this.simple = _s;
    }

    public BreakPointCondition getResultSimple() {
        return resultSimple;
    }

    public BreakPointCondition result(int _mode) {
        return getResultSimple().stopBpc(isSimple());
    }
}