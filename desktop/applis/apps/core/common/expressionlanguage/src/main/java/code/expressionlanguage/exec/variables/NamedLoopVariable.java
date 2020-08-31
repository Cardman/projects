package code.expressionlanguage.exec.variables;

public final class NamedLoopVariable extends NamedVariable{
    private final LoopVariable localVariable;

    public NamedLoopVariable(String name, LoopVariable localVariable) {
        super(name);
        this.localVariable = localVariable;
    }

    public LoopVariable getLocalVariable() {
        return localVariable;
    }
}
