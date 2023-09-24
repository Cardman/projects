package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class MethodPointBlockPair implements AbsPairPoint {
    public static final int CMP=3;
    private final MethodPointBlockKey mp;
    private final String sgn;
    private final MethodPoint value;

    public MethodPointBlockPair(MemberCallingsBlock _i, AbstractInterceptorStdCaller _v, String _sgn, boolean _enabled, String _k) {
        this.mp = new MethodPointBlockKey(_i, _k);
        this.sgn = _sgn;
        this.value = new MethodPoint(_v, this, mp,CMP);
        this.value.setEnabled(_enabled);
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyze(_d,this,_r,_a,_g);
    }

    public String getSgn() {
        return sgn;
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
