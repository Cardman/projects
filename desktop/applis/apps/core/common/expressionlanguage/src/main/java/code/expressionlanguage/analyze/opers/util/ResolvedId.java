package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.types.AnaResultPartTypeDtoInt;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;

public final class ResolvedId {
    private final MethodId id;
    private final InfoErrorDto info;
    private final CustList<AnaResultPartTypeDtoInt> results;

    public ResolvedId(MethodId _id, InfoErrorDto _info, CustList<AnaResultPartTypeDtoInt> _results) {
        this.id = _id;
        this.info = _info;
        this.results = _results;
    }

    public InfoErrorDto getInfo() {
        return info;
    }

    public CustList<AnaResultPartTypeDtoInt> getResults() {
        return results;
    }

    public MethodId getId() {
        return id;
    }
}
