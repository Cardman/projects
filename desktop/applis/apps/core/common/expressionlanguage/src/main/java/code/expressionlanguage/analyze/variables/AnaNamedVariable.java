package code.expressionlanguage.analyze.variables;

public abstract class AnaNamedVariable {
    private final String name;

    public AnaNamedVariable(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

}