package code.expressionlanguage.analyze.variables;

public abstract class AnaNamedVariable {
    private final String name;

    public AnaNamedVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}