package code.expressionlanguage.analyze.instr;

import code.expressionlanguage.analyze.blocks.FieldPartOffset;
import code.util.StringList;

public final class PartOffsetAffect {
    private final FieldPartOffset partOffset;
    private final boolean affect;
    private final StringList errs = new StringList();

    public PartOffsetAffect(FieldPartOffset _partOffset, boolean _affect) {
        partOffset = _partOffset;
        affect = _affect;
    }

    public FieldPartOffset getPartOffset() {
        return partOffset;
    }

    public boolean isAffect() {
        return affect;
    }

    public StringList getErrs() {
        return errs;
    }
}
