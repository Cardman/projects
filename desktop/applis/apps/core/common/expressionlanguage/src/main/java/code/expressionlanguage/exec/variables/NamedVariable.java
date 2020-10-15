package code.expressionlanguage.exec.variables;

public abstract class NamedVariable {
    private final String name;

    protected NamedVariable(String _name) {
        this.name = _name;
    }

    public String getName() {
        return name;
    }

}