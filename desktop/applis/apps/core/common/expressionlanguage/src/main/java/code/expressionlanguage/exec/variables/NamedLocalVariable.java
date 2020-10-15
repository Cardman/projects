package code.expressionlanguage.exec.variables;

public final class NamedLocalVariable extends NamedVariable{
    private final LocalVariable localVariable;

    public NamedLocalVariable(String _name, LocalVariable _localVariable) {
        super(_name);
        this.localVariable = _localVariable;
    }

    public LocalVariable getLocalVariable() {
        return localVariable;
    }
}