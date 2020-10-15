package code.expressionlanguage.analyze.variables;

public final class AnaNamedLocalVariable extends AnaNamedVariable {
    private final AnaLocalVariable localVariable;

    public AnaNamedLocalVariable(String _name, AnaLocalVariable _localVariable) {
        super(_name);
        this.localVariable = _localVariable;
    }

    public AnaLocalVariable getLocalVariable() {
        return localVariable;
    }
}