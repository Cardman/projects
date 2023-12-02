package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class TypePoint {
    public static final int BPC_STATIC = 1;
    public static final int BPC_INSTANCE = 2;
    private boolean enabled;
    private boolean instanceType = true;
    private boolean staticType;
    private final BreakPointCondition resultStatic;
    private final BreakPointCondition resultInstance;
    public TypePoint(AbstractInterceptorStdCaller _i, AbsPairPoint _superKey, AbsKeyPoint _key){
        resultStatic = new BreakPointCondition(_i, _superKey, _key,BreakPoint.BP, BPC_STATIC);
        resultInstance = new BreakPointCondition(_i, _superKey, _key,BreakPoint.BP, BPC_INSTANCE);
    }

    public void resetCount() {
        resultStatic.resetCount();
        resultInstance.resetCount();
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _e) {
        this.enabled = _e;
    }

    public boolean isInstanceType() {
        return instanceType;
    }

    public void setInstanceType(boolean _i) {
        this.instanceType = _i;
    }

    public boolean isStaticType() {
        return staticType;
    }

    public void setStaticType(boolean _s) {
        this.staticType = _s;
    }

    public BreakPointCondition getResultStatic() {
        return resultStatic;
    }

    public BreakPointCondition getResultInstance() {
        return resultInstance;
    }

}