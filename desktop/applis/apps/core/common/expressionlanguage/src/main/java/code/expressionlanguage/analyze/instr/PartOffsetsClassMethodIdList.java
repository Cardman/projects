package code.expressionlanguage.analyze.instr;

import code.util.CustList;

public final class PartOffsetsClassMethodIdList {
    private final CustList<PartOffset> types;
    private final CustList<PartOffsetsClassMethodId> overrides;

    public PartOffsetsClassMethodIdList(CustList<PartOffset> _types, CustList<PartOffsetsClassMethodId> _overrides) {
        this.types = _types;
        this.overrides = _overrides;
    }

    public CustList<PartOffset> getTypes() {
        return types;
    }

    public CustList<PartOffsetsClassMethodId> getOverrides() {
        return overrides;
    }
}
