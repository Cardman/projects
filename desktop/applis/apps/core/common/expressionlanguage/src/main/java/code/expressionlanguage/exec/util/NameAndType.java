package code.expressionlanguage.exec.util;

public final class NameAndType {
    private final String name;
    private final String type;

    public NameAndType(String _name, String _type) {
        this.name = _name;
        this.type = _type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
