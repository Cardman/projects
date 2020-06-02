package code.expressionlanguage.types;

public final class ParentChildType {
    private final ParentPartType parentPartType;
    private final PartType child;

    public ParentChildType(ParentPartType parentPartType, PartType child) {
        this.parentPartType = parentPartType;
        this.child = child;
    }

    public ParentPartType getParentPartType() {
        return parentPartType;
    }

    public PartType getChild() {
        return child;
    }
}
