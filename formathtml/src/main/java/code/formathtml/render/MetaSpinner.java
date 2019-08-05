package code.formathtml.render;

public final class MetaSpinner extends MetaInput implements IntSpinner {

    private final long formNb;
    private String value;

    public MetaSpinner(MetaContainer _parent, int _group, String _value, long _formNb) {
        super(_parent, _group);
        value = _value;
        formNb = _formNb;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public long getFormNb() {
        return formNb;
    }
}
