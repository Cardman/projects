package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;

public final class ReversibleConversion {
    private final AnaFormattedRootBlock from;
    private final MemberId memberIdFrom;
    private final AnaTypeFct functionFrom;
    private final AnaFormattedRootBlock to;
    private final MemberId memberIdTo;
    private final AnaTypeFct functionTo;

    public ReversibleConversion(AnaFormattedRootBlock _from, MemberId _memberIdFrom,AnaTypeFct _functionFrom,
                                AnaFormattedRootBlock _to, MemberId _memberIdTo,AnaTypeFct _functionTo) {
        from = _from;
        memberIdFrom = _memberIdFrom;
        functionFrom = _functionFrom;
        to = _to;
        memberIdTo = _memberIdTo;
        functionTo = _functionTo;
    }

    public AnaFormattedRootBlock getFrom() {
        return from;
    }

    public MemberId getMemberIdFrom() {
        return memberIdFrom;
    }

    public AnaTypeFct getFunctionFrom() {
        return functionFrom;
    }

    public AnaFormattedRootBlock getTo() {
        return to;
    }

    public MemberId getMemberIdTo() {
        return memberIdTo;
    }

    public AnaTypeFct getFunctionTo() {
        return functionTo;
    }
}
