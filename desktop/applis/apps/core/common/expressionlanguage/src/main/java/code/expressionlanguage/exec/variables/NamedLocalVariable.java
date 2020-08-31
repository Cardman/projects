package code.expressionlanguage.exec.variables;

public final class NamedLocalVariable extends NamedVariable{
    private final LocalVariable localVariable;

    public NamedLocalVariable(String name, LocalVariable localVariable) {
        super(name);
        this.localVariable = localVariable;
    }

    public LocalVariable getLocalVariable() {
        return localVariable;
    }
}