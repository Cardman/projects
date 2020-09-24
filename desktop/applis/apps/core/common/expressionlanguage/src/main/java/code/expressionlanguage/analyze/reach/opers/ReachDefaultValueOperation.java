package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.DefaultValueOperation;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class ReachDefaultValueOperation extends ReachMethodOperation implements ReachCalculable {

    private String className;

    ReachDefaultValueOperation(DefaultValueOperation _info) {
        super(_info);
        className = _info.getClassName();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (className.contains(AnaTemplates.PREFIX_VAR_TYPE)) {
            return;
        }
        byte cast_ = ClassArgumentMatching.getPrimitiveCast(className, _page.getStandards());
        Argument a_ = new Argument(NumParsers.convert(cast_));
        setSimpleArgumentAna(a_);
    }
}
