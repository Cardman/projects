package code.expressionlanguage.exec.types;

public class ExecParentChildType {
    private final ExecParentPartType parentPartType;
    private final ExecPartType child;

    public ExecParentChildType(ExecParentPartType _parentPartType, ExecPartType _child) {
        this.parentPartType = _parentPartType;
        this.child = _child;
    }

    public ExecParentPartType getParentPartType() {
        return parentPartType;
    }

    public ExecPartType getChild() {
        return child;
    }
}
