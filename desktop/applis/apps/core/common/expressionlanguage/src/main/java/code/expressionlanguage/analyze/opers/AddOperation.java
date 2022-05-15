package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.core.StringUtil;


public final class AddOperation extends NumericOperation {

    private boolean catString;

    public AddOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        ResultOperand res_ = unwrappBinNumResultClass(_a,_b,_page);
        if (!res_.getResult().getSingleNameOrEmpty().isEmpty()) {
            return res_;
        }
        if (StringUtil.quickEq(_op.trim(), PLUS)) {
            boolean str_ = isStr(_a,_page);
            if (isStr(_b,_page)) {
                str_ = true;
            }
            if (str_) {
                _a.setConvertToString(true);
                _b.setConvertToString(true);
                res_.setResult(new AnaClassArgumentMatching(_page.getAliasString()));
                catString = true;
                return res_;
            }
        }
        return errNum(_a, _b, _page);
    }

    private boolean isStr(AnaClassArgumentMatching _a, AnalyzedPageEl _page) {
        String stringType_ = _page.getAliasString();
        return _a.matchClass(stringType_) || _a.isVariable();
    }

    public boolean isCatString() {
        return catString;
    }
}
