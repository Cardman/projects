package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.AnaGeneType;

public final class ResolvedIdType {
    private final String fullName;
    private final AnaGeneType geneType;

    public ResolvedIdType(String _fullName, AnaGeneType _geneType) {
        this.fullName = _fullName;
        this.geneType = _geneType;
    }

    public AnaGeneType getGeneType() {
        return geneType;
    }

    public String getFullName() {
        return fullName;
    }
}
