package code.expressionlanguage.analyze.instr;

import code.util.CustList;

public final class PartOffsetsClassMethodIdList {
    private final CustList<PartOffset> types;
    private final CustList<PartOffsetsClassMethodId> overrides;

    public PartOffsetsClassMethodIdList(CustList<PartOffset> types, CustList<PartOffsetsClassMethodId> overrides) {
        this.types = types;
        this.overrides = overrides;
    }

    public CustList<PartOffset> getTypes() {
        return types;
    }

    public CustList<PartOffsetsClassMethodId> getOverrides() {
        return overrides;
    }
}
