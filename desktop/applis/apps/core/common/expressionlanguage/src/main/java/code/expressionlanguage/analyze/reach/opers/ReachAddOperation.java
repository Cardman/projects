package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnaApplyCoreMethodUtil;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.AddOperation;
import code.expressionlanguage.common.NumParsers;
import code.util.StringList;

public final class ReachAddOperation extends ReachNumericOperation {
    private boolean catString;
    ReachAddOperation(AddOperation _info) {
        super(_info);
        catString = _info.isCatString();
    }

    @Override
    Argument calculateOperAna(Argument _a, String _op, Argument _b, AnalyzedPageEl _page) {
        if (StringList.quickEq(_op.trim(), PLUS)) {
            if (catString) {
                return AnaApplyCoreMethodUtil.localSumDiff(_a, _b, _page);
            }
            return new Argument(NumParsers.calculateSum(NumParsers.convertToNumber(_a.getStruct()),
                    NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
        }
        return new Argument(NumParsers.calculateDiff(NumParsers.convertToNumber(_a.getStruct()),
                NumParsers.convertToNumber(_b.getStruct()), getResultClass().getUnwrapObjectNb()));
    }

}
