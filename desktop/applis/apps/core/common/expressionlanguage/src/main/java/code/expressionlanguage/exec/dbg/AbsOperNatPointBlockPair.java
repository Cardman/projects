package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.fwd.AbsLightContextGenerator;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ResultContextLambda;

public abstract class AbsOperNatPointBlockPair implements AbsPairPoint {
    private final OperNatPointBlockKey on;
    private final String symbol;

    protected AbsOperNatPointBlockPair(String _k, String _symbol, String _f, String _s) {
        this.on = new OperNatPointBlockKey(_k, _f, _s);
        this.symbol = _symbol;
    }

    @Override
    public ResultContextLambda analyze(String _d, ResultContext _r, String _a, AbsLightContextGenerator _g, int _p) {
        return ResultContextLambda.dynamicAnalyze(_d,this,_r,_a,_g);
    }

    @Override
    public String keyStr() {
        return getOn().keyStr();
    }

    public String getSymbol() {
        return symbol;
    }

    public OperNatPointBlockKey getOn() {
        return on;
    }

    public abstract void resetCount();

}
