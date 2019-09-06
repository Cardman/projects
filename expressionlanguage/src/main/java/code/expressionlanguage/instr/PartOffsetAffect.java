package code.expressionlanguage.instr;

public final class PartOffsetAffect {
    private final PartOffset partOffset;
    private final boolean affect;

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
}
