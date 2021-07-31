package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.common.AnaGeneType;

public final class TypeMainDelimiters {
    private final AnaGeneType interType;
    private final int begin;
    private final int end;

    public TypeMainDelimiters(AnaGeneType _interType, int _begin, int _end) {
        this.interType = _interType;
        this.begin = _begin;
        this.end = _end;
    }

    public AnaGeneType getInterType() {
        return interType;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }
}
