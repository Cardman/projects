package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class MethodPointBlockPair {
    private static final int CMP=3;
    private final MethodPointBlockKey mp;
    private final MethodPoint value;

    public MethodPointBlockPair(MemberCallingsBlock _i, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.mp = new MethodPointBlockKey(_i);
        this.value = new MethodPoint(_v,mp,CMP);
        this.value.setEnabled(_enabled);
    }

    public MethodPointBlockKey getMp() {
        return mp;
    }

    public MethodPoint getValue() {
        return value;
    }
}
