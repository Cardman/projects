package code.formathtml.render;

public final class MetaTextArea extends MetaInput implements IntTextArea {

    private final long formNb;
    private final int cols;

    private final int rows;

    private String value;

    public MetaTextArea(MetaContainer _parent, int _group, int _cols, int _rows, String _value, long _formNb) {
        super(_parent, _group);
        cols = _cols;
        rows = _rows;
        value = _value;
        formNb = _formNb;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public long getFormNb() {
        return formNb;
    }

    @Override
    public String getValue() {
        return value;
    }
}
