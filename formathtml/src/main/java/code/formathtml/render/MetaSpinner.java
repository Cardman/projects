package code.formathtml.render;

public final class MetaSpinner extends MetaInput {

    private final String value;

    public MetaSpinner(MetaContainer _parent, int _group, String _value) {
        super(_parent, _group);
        value = _value;
    }

    public String getValue() {
        return value;
    }
}
