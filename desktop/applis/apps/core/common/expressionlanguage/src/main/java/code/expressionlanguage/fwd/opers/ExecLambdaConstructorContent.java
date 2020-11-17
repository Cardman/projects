package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.FetchMemberUtil;

public final class ExecLambdaConstructorContent {
    private final ConstructorId realId;
    private final ExecTypeFunction pair;
    public ExecLambdaConstructorContent(ConstructorId _realId, MemberId _id, Forwards _forwards) {
        realId = _realId;
        pair = FetchMemberUtil.fetchTypeCtor(_id, _forwards);
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public ConstructorId getRealId() {
        return realId;
    }

}
