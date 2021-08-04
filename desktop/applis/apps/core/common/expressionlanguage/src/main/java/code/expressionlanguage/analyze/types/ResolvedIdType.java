package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.AnaGeneType;

public final class ResolvedIdType {
    private final String fullName;
    private final AnaGeneType geneType;
    private final AnaResultPartType dels;

    public ResolvedIdType(String _fullName, AnaGeneType _geneType, AnaResultPartType _dels) {
        this.fullName = _fullName;
        this.geneType = _geneType;
        dels = _dels;
    }

    public AnaResultPartType getDels() {
        return dels;
    }

    public AnaGeneType getGeneType() {
        return geneType;
    }

    public String getFullName() {
        return fullName;
    }
}
