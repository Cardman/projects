package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.threads.AbstractAtomicInteger;

public final class MethodPointBlockPair implements AbsPairPoint {
    private static final int CMP=3;
    private final MethodPointBlockKey mp;
    private final String sgn;
    private final AbstractAtomicInteger pref;
    private final MethodPoint value;

    public MethodPointBlockPair(MemberCallingsBlock _i, AbstractInterceptorStdCaller _v, String _sgn, int _pref, boolean _enabled, String _k) {
        this.mp = new MethodPointBlockKey(_i, _k);
        this.sgn = _sgn;
        this.pref = _v.newAtInt();
        this.pref.set(_pref);
        this.value = new MethodPoint(_v,mp,CMP);
        this.value.setEnabled(_enabled);
    }

    public String getSgn() {
        return sgn;
    }

    public AbstractAtomicInteger getPref() {
        return pref;
    }

    @Override
    public String keyStr() {
        return getMp().keyStr();
    }

    public MethodPointBlockKey getMp() {
        return mp;
    }

    public MethodPoint getValue() {
        return value;
    }
}
