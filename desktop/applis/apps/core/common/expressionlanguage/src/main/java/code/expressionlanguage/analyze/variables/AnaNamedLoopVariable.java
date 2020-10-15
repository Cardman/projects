package code.expressionlanguage.analyze.variables;

public final class AnaNamedLoopVariable extends AnaNamedVariable {
    private final AnaLoopVariable localVariable;

    public AnaNamedLoopVariable(String _name, AnaLoopVariable _localVariable) {
        super(_name);
        this.localVariable = _localVariable;
    }

    public AnaLoopVariable getLocalVariable() {
        return localVariable;
    }
}
