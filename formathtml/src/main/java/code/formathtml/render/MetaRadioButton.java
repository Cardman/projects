package code.formathtml.render;

public final class MetaRadioButton extends MetaInput {

    private final String value;

    private final boolean checked;

    private final int indexButton;

    public MetaRadioButton(MetaContainer _parent, String _name, int _group, int _indexButton,boolean _checked, String _value) {
        super(_parent, _group, _name);
        checked = _checked;
        indexButton = _indexButton;
        value = _value;
    }

    public int getIndexButton() {
        return indexButton;
    }

    public String getValue() {
        return value;
    }

    public boolean isChecked() {
        return checked;
    }
}
