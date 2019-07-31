package code.formathtml.render;

public final class MetaTextField extends MetaInput {

    private final int cols;

    private final String value;

    public MetaTextField(MetaContainer _parent, int _group, int _cols, String _value) {
        super(_parent, _group);
        cols = _cols;
        value = _value;
    }

    public int getCols() {
        return cols;
    }

    public String getValue() {
        return value;
    }
}
