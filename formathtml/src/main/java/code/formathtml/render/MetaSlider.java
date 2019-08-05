package code.formathtml.render;

public final class MetaSlider extends MetaInput implements IntSlider {

    private final long formNb;
    private String value;

    public MetaSlider(MetaContainer _parent, int _group, String _value, long _formNb) {
        super(_parent, _group);
        value = _value;
        formNb = _formNb;
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
