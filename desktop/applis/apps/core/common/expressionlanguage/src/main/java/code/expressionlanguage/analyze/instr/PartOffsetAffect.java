package code.expressionlanguage.analyze.instr;

import code.util.StringList;

public final class PartOffsetAffect {
    private final PartOffset partOffset;
    private final boolean affect;
    private final StringList errs = new StringList();

    public PartOffsetAffect(PartOffset _partOffset, boolean _affect) {
        partOffset = _partOffset;
        affect = _affect;
    }

    public PartOffset getPartOffset() {
        return partOffset;
    }

    public boolean isAffect() {
        return affect;
    }

    public StringList getErrs() {
        return errs;
    }
}
