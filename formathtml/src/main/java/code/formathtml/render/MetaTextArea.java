package code.formathtml.render;

public final class MetaTextArea extends MetaInput {

    private final int cols;

    private final int rows;

    private final String value;

    public MetaTextArea(MetaContainer _parent, int _group, int _cols, int _rows, String _value) {
        super(_parent, _group);
        cols = _cols;
        rows = _rows;
        value = _value;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public String getValue() {
        return value;
    }
}
