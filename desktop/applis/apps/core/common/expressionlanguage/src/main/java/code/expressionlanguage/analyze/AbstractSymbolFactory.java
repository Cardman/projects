package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.ParamReturn;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;

public interface AbstractSymbolFactory {
    ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _unary, AnalyzedPageEl _page);
    ResultOperand generateOperand(String _symbol, AnaClassArgumentMatching _left, AnaClassArgumentMatching _right, AnalyzedPageEl _page);
    CustList<CustList<ParamReturn>> binaries(String _symbol, AnalyzedPageEl _page);
    CustList<CustList<ParamReturn>> unaries(String _symbol, AnalyzedPageEl _page);
}
