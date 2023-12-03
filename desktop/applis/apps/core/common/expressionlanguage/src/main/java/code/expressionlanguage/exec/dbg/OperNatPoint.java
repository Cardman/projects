package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class OperNatPoint {
    public static final int OP= 7;
    public static final int BPC_SIMPLE = 0;
    public static final int BPC_COMPOUND = 1;
    private final boolean possibleComp;
    private boolean enabled;
    private boolean simple = true;
    private final BreakPointCondition resultSimple;
    private boolean compound;
    private final BreakPointCondition resultCompound;
    public OperNatPoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key, boolean _enableComp){
        resultSimple = new BreakPointCondition(_i, _superKey, _key,OP, BPC_SIMPLE);
        resultCompound = new BreakPointCondition(_i, _superKey, _key,OP, BPC_COMPOUND);
        possibleComp = _enableComp;
    }

    public void resetCount() {
        resultSimple.resetCount();
        resultCompound.resetCount();
    }

    public boolean isPossibleComp() {
        return possibleComp;
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

    public boolean isCompound() {
        return compound;
    }

    public void setCompound(boolean _c) {
        this.compound = _c;
    }

    public BreakPointCondition getResultSimple() {
        return resultSimple;
    }

    public BreakPointCondition getResultCompound() {
        return resultCompound;
    }

    public BreakPointCondition result(int _mode) {
        if (_mode == BPC_SIMPLE) {
            return getResultSimple().stopBpc(isSimple());
        }
        return getResultCompound().stopBpc(isCompound());
    }
}