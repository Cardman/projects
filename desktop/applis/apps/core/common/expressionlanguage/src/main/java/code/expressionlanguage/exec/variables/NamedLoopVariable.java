package code.expressionlanguage.exec.variables;

public final class NamedLoopVariable extends NamedVariable{
    private final LoopVariable localVariable;

    public NamedLoopVariable(String _name, LoopVariable _localVariable) {
        super(_name);
        this.localVariable = _localVariable;
    }

    public LoopVariable getLocalVariable() {
        return localVariable;
    }
}
