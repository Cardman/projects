package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ReversibleConversion {
    private final ClassMethodId from;
    private final MemberId memberIdFrom;
    private final ClassMethodId to;
    private final MemberId memberIdTo;

    public ReversibleConversion(ClassMethodId _from, MemberId _memberIdFrom,
                                ClassMethodId _to, MemberId _memberIdTo) {
        from = _from;
        memberIdFrom = _memberIdFrom;
        to = _to;
        memberIdTo = _memberIdTo;
    }

    public ClassMethodId getFrom() {
        return from;
    }

    public MemberId getMemberIdFrom() {
        return memberIdFrom;
    }

    public ClassMethodId getTo() {
        return to;
    }

    public MemberId getMemberIdTo() {
        return memberIdTo;
    }

}
