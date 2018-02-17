package code.formathtml.render;

public final class MetaRadioButton extends MetaInput {

    private final String value;

    private final boolean checked;

    public MetaRadioButton(MetaContainer _parent, int _group, boolean _checked, String _value) {
        super(_parent, _group);
        checked = _checked;
        value = _value;
    }

    public String getValue() {
        return value;
    }

    public boolean isChecked() {
        return checked;
    }
}
