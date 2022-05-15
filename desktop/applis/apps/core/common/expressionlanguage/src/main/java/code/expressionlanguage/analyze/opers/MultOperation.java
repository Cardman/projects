package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;


public final class MultOperation extends NumericOperation {

    public MultOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op, AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        ResultOperand res_ = unwrappBinNumResultClass(_a,_b,_page);
        if (!res_.getResult().getSingleNameOrEmpty().isEmpty()) {
            return res_;
        }
        return errNum(_a, _b, _page);
    }

}
