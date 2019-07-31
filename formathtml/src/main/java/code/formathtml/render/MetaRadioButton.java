package code.formathtml.render;

import code.formathtml.util.FormInputCoords;

public final class MetaRadioButton extends MetaInput {

    private final String value;

    private final boolean checked;

    private final int indexButton;

    private final FormInputCoords id;
    public MetaRadioButton(MetaContainer _parent, int _group, int _indexButton, boolean _checked, String _value, FormInputCoords _id) {
        super(_parent, _group);
        checked = _checked;
        indexButton = _indexButton;
        value = _value;
        id = _id;
    }

    public FormInputCoords getId() {
        return id;
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
