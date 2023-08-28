package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;

public final class StdMethodPointBlockPair {
    private static final int SMP=4;
    private final StdMethodPointBlockKey sm;
    private final MethodPoint value;

    public StdMethodPointBlockPair(StandardNamedFunction _i, StandardType _t, String _k, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.sm = new StdMethodPointBlockKey(_i, _t, _k);
        this.value = new MethodPoint(_v,sm,SMP);
        this.value.setEnabled(_enabled);
    }

    public StdMethodPointBlockKey getSm() {
        return sm;
    }

    public MethodPoint getValue() {
        return value;
    }
}
