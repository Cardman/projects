package code.expressionlanguage.structs;

import code.expressionlanguage.functionid.MethodId;

public final class PairMethodIds {
    private final MethodId realId;
    private final MethodId fid;

    public PairMethodIds(MethodId _realId) {
        this.realId = _realId;
        this.fid = _realId;
    }

    public PairMethodIds(MethodId _realId, MethodId _fid) {
        this.realId = _realId;
        this.fid = _fid;
    }

    public MethodId getFid() {
        return fid;
    }

    public MethodId getRealId() {
        return realId;
    }
}
