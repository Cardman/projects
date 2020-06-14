package code.expressionlanguage.exec.types;

public class ExecParentChildType {
    private final ExecParentPartType parentPartType;
    private final ExecPartType child;

    public ExecParentChildType(ExecParentPartType parentPartType, ExecPartType child) {
        this.parentPartType = parentPartType;
        this.child = child;
    }

    public ExecParentPartType getParentPartType() {
        return parentPartType;
    }

    public ExecPartType getChild() {
        return child;
    }
}
