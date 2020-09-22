package code.expressionlanguage.analyze.instr;

import code.util.StringList;

public final class PartOffsetAffect {
    private final PartOffset partOffset;
    private final boolean affect;
    private final StringList errs;

    public PartOffsetAffect(PartOffset _partOffset, boolean _affect, StringList _errs) {
        partOffset = _partOffset;
        affect = _affect;
        errs = _errs;
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
