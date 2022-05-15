package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.ResultOperand;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;

public final class BitOrOperation extends NumericOperation {

    public BitOrOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ResultOperand analyzeOper(AnaClassArgumentMatching _a, String _op,
                              AnaClassArgumentMatching _b, AnalyzedPageEl _page) {
        return analyzeBitwise(_a, _b, _page);
    }

}
