package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ReversibleConversion {
    private final ClassMethodId from;
    private final MemberId memberIdFrom;
    private final AnaTypeFct functionFrom;
    private final ClassMethodId to;
    private final MemberId memberIdTo;
    private final AnaTypeFct functionTo;

    public ReversibleConversion(ClassMethodId _from, MemberId _memberIdFrom,AnaTypeFct _functionFrom,
                                ClassMethodId _to, MemberId _memberIdTo,AnaTypeFct _functionTo) {
        from = _from;
        memberIdFrom = _memberIdFrom;
        functionFrom = _functionFrom;
        to = _to;
        memberIdTo = _memberIdTo;
        functionTo = _functionTo;
    }

    public ClassMethodId getFrom() {
        return from;
    }

    public MemberId getMemberIdFrom() {
        return memberIdFrom;
    }

    public AnaTypeFct getFunctionFrom() {
        return functionFrom;
    }

    public ClassMethodId getTo() {
        return to;
    }

    public MemberId getMemberIdTo() {
        return memberIdTo;
    }

    public AnaTypeFct getFunctionTo() {
        return functionTo;
    }
}
