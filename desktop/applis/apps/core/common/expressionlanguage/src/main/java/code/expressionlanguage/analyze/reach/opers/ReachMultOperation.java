package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.NumericOperation;
import code.expressionlanguage.common.NumParsers;
import code.util.core.StringUtil;

public final class ReachMultOperation extends ReachNumericOperation {
    ReachMultOperation(NumericOperation _info) {
        super(_info);
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b, AnalyzedPageEl _page) {
        if (StringUtil.quickEq(_op.trim(), MULT)) {
            return new Argument(NumParsers.calculateMult(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        if (StringUtil.quickEq(_op.trim(), DIV)) {
            return new Argument(NumParsers.calculateDiv(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        return new Argument(NumParsers.calculateMod(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));

    }
}
