package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;

public final class ResolvedIdType {
    private final ResolvedIdTypeContent content;
    private final AnaResultPartType dels;

    public ResolvedIdType(String _fullName, AnaGeneType _geneType, AnaResultPartType _dels) {
        content = new ResolvedIdTypeContent(_fullName, _geneType);
        dels = _dels;
    }

    public static boolean onlyOneElt(CustList<ResolvedIdTypeContent> _list) {
        if (_list.isEmpty()) {
            return false;
        }
        AnaGeneType geneType_ = _list.first().getGeneType();
        int size_ = _list.size();
        for (int i = 1; i < size_; i++) {
            if (_list.get(i).getGeneType() != geneType_) {
                return false;
            }
        }
        return true;
    }

    public AnaResultPartType getDels() {
        return dels;
    }

    public ResolvedIdTypeContent getContent() {
        return content;
    }

    public AnaGeneType getGeneType() {
        return content.getGeneType();
    }

    public String getFullName() {
        return content.getFullName();
    }
}
