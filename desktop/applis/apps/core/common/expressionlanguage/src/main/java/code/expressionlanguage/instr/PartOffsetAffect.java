package code.expressionlanguage.instr;

import code.util.StringList;

public final class PartOffsetAffect {
    private final PartOffset partOffset;
    private final boolean affect;
    private final boolean add;
    private final StringList errs;

    public PartOffsetAffect(PartOffset _partOffset, boolean _affect, boolean _add, StringList _errs) {
        partOffset = _partOffset;
        affect = _affect;
        add = _add;
        errs = _errs;
    }

    public PartOffset getPartOffset() {
        return partOffset;
    }

    public boolean isAffect() {
        return affect;
    }

    public boolean isAdd() {
        return add;
    }

    public StringList getErrs() {
        return errs;
    }
}
