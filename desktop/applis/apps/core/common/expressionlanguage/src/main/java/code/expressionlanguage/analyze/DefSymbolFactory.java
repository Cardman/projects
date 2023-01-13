package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.ParamReturn;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;
import code.util.StringList;

public final class DefSymbolFactory implements AbstractSymbolFactory {
    @Override
    public ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _unary, AnalyzedPageEl _page) {
        return defResult();
    }

    @Override
    public ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page) {
        return defResult();
    }

    @Override
    public CustList<CustList<ParamReturn>> binaries(String _symbol, AnalyzedPageEl _page) {
        return new CustList<CustList<ParamReturn>>();
    }

    @Override
    public CustList<StringList> unaries(String _symbol, AnalyzedPageEl _page) {
        return new CustList<StringList>();
    }

    public static ResultOperand defResult() {
        ResultOperand r_ = new ResultOperand();
        r_.setResult(new AnaClassArgumentMatching(""));
        return r_;
    }
}
