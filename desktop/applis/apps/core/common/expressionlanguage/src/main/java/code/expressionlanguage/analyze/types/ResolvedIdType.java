package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;

public final class ResolvedIdType {
    private final String fullName;
    private final AnaGeneType geneType;
    private final CustList<AnaResultPartType> dels;

    public ResolvedIdType(String _fullName, AnaGeneType _geneType, CustList<AnaResultPartType> _dels) {
        this.fullName = _fullName;
        this.geneType = _geneType;
        dels = _dels;
    }

    public CustList<AnaResultPartType> getDels() {
        return dels;
    }

    public AnaGeneType getGeneType() {
        return geneType;
    }

    public String getFullName() {
        return fullName;
    }
}
