package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;

public final class WatchPointBlockPair implements AbsPairPoint {
    private final WatchPointBlockKey wp;
    private final RootBlock root;
    private final WatchPoint value;

    public WatchPointBlockPair(boolean _trField, RootBlock _rBlock, int _nb, String _field, AbstractInterceptorStdCaller _v, boolean _enabled) {
        this.wp = new WatchPointBlockKey(_trField, _nb, _field);
        this.root = _rBlock;
        this.value = new WatchPoint(_v, this, wp);
        this.value.setEnabled(_enabled);
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyzeField(_d,this,_r,_a,_g,_p);
    }

    @Override
    public String keyStr() {
        return getWp().keyStr();
    }

    public WatchPointBlockKey getWp() {
        return wp;
    }

    public RootBlock getRoot() {
        return root;
    }

    public WatchPoint getValue() {
        return value;
    }
}
