package code.formathtml.render;

public final class MetaTextField extends MetaInput implements IntTextField {

    private final int cols;

    private final long formNb;

    private String value;

    public MetaTextField(MetaContainer _parent, int _group, int _cols, String _value, long _formNb) {
        super(_parent, _group);
        cols = _cols;
        value = _value;
        formNb = _formNb;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String getValue() {
        return value;
    }

    public long getFormNb() {
        return formNb;
    }
}
