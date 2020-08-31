package code.expressionlanguage.analyze.variables;

public abstract class AnaNamedVariable {
    private final String name;

    private final boolean finalVariable;

    private final int ref;

    public AnaNamedVariable(String name, boolean finalVariable, int ref) {
        this.name = name;
        this.finalVariable = finalVariable;
        this.ref=ref;
    }

    public boolean isFinalVariable() {
        return finalVariable;
    }

    public int getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

}