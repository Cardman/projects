package code.expressionlanguage;


public final class CustEnum {
    private final String className;

    private final String name;

    private final int ordinal;

    public CustEnum(String _className, String _name, int _ordinal) {
        className = _className;
        name = _name;
        ordinal = _ordinal;
    }

    public String name() {
        return name;
    }

    public int ordinal() {
        return ordinal;
    }

    public String getDeclaringClass() {
        return className;
    }
}
