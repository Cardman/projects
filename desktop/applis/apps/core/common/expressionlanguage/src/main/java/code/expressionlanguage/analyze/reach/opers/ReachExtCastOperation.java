package code.expressionlanguage.analyze.reach.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.ExplicitOperation;
import code.expressionlanguage.analyze.opers.ImplicitOperation;
import code.expressionlanguage.analyze.types.AnaTypeUtil;

public final class ReachExtCastOperation extends ReachMethodOperation implements ReachCalculable {
    private String className;
    ReachExtCastOperation(ExplicitOperation _info) {
        super(_info);
        className = _info.getClassName();
    }
    ReachExtCastOperation(ImplicitOperation _info) {
        super(_info);
        className = _info.getClassName();
    }

    @Override
    public void tryCalculateNode(AnalyzedPageEl _page) {
        if (!allAreDefined(this)) {
            return;
        }
        Argument arg_ = getFirstChild().getArgument();
        if (AnaTypeUtil.isPrimitive(className, _page)) {
            checkNull(arg_, _page);
        }
    }

}
