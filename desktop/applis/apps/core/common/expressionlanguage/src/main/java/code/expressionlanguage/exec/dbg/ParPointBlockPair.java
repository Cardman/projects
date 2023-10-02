package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class ParPointBlockPair implements AbsPairPoint {
    private final ExcPointBlockKey pp;
    private final RootBlock rootBlock;
    private final ParPoint value;

    public ParPointBlockPair(int _ex, String _cl, AbstractInterceptorStdCaller _v, boolean _enabled, RootBlock _de) {
        this.pp = new ExcPointBlockKey(_ex, _cl);
        this.value = new ParPoint(_v, this, pp);
        this.value.setEnabled(_enabled);
        rootBlock = _de;
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyzePar(_d,this,_r,_a,_g);
    }

    public RootBlock getRootBlock() {
        return rootBlock;
    }

    @Override
    public String keyStr() {
        return getPp().keyStr();
    }

    public ExcPointBlockKey getPp() {
        return pp;
    }

    public ParPoint getValue() {
        return value;
    }
}
