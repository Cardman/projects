package code.expressionlanguage.analyze.variables;

public final class AnaNamedLoopVariable extends AnaNamedVariable {
    private final AnaLoopVariable localVariable;

    public AnaNamedLoopVariable(String name, AnaLoopVariable localVariable) {
        super(name);
        this.localVariable = localVariable;
    }

    public AnaLoopVariable getLocalVariable() {
        return localVariable;
    }
}
