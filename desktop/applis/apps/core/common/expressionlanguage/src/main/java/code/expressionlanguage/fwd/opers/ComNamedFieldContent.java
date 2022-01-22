package code.expressionlanguage.fwd.opers;

public abstract class ComNamedFieldContent {
    private final String name;
    private final String type;
    private final String idClass;

    protected ComNamedFieldContent(String _name, String _type, String _idClass) {
        this.name = _name;
        this.type = _type;
        this.idClass = _idClass;
    }

    public String getIdClass() {
        return idClass;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
