package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ReversibleConversion {
    private final ClassMethodId from;
    private final int rootNumberFrom;
    private final int memberNumberFrom;
    private final ClassMethodId to;
    private final int rootNumberTo;
    private final int memberNumberTo;

    public ReversibleConversion(ClassMethodId _from, int _rootNumberFrom, int _memberNumberFrom,
                                ClassMethodId _to, int _rootNumberTo, int _memberNumberTo) {
        from = _from;
        rootNumberFrom = _rootNumberFrom;
        memberNumberFrom = _memberNumberFrom;
        to = _to;
        rootNumberTo = _rootNumberTo;
        memberNumberTo = _memberNumberTo;
    }

    public ClassMethodId getFrom() {
        return from;
    }

    public int getRootNumberFrom() {
        return rootNumberFrom;
    }

    public int getMemberNumberFrom() {
        return memberNumberFrom;
    }

    public ClassMethodId getTo() {
        return to;
    }

    public int getRootNumberTo() {
        return rootNumberTo;
    }

    public int getMemberNumberTo() {
        return memberNumberTo;
    }
}
