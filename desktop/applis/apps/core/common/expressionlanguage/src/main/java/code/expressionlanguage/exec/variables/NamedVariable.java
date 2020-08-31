package code.expressionlanguage.exec.variables;

public abstract class NamedVariable {
    private final String name;

    public NamedVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}