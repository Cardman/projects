package code.formathtml.render;

public final class MetaSlider extends MetaInput {

    private final String value;

    public MetaSlider(MetaContainer _parent, int _group, String _value) {
        super(_parent, _group);
        value = _value;
    }

    public String getValue() {
        return value;
    }
}
