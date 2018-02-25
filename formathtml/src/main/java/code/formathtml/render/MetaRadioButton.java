package code.formathtml.render;

public final class MetaRadioButton extends MetaInput {

    private final String value;

    private final boolean checked;

    private final int indexButton;

    public MetaRadioButton(MetaContainer _parent, int _group, int _indexButton,boolean _checked, String _value) {
        super(_parent, _group);
        checked = _checked;
        indexButton = _indexButton;
        value = _value;
    }

    public String getValue() {
        return value;
    }

    public boolean isChecked() {
        return checked;
    }
}
