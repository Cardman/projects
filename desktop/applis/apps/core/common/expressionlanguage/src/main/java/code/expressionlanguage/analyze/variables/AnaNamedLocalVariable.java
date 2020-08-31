package code.expressionlanguage.analyze.variables;

public final class AnaNamedLocalVariable extends AnaNamedVariable {
    private final AnaLocalVariable localVariable;

    public AnaNamedLocalVariable(String name, AnaLocalVariable localVariable) {
        super(name,localVariable.isFinalVariable(),localVariable.getRef());
        this.localVariable = localVariable;
    }

    public AnaLocalVariable getLocalVariable() {
        return localVariable;
    }
}