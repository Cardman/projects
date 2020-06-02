package code.formathtml.render;

import code.formathtml.util.FormInputCoords;

public final class MetaRadioButton extends MetaInput implements IntRadioButton {

    private final String value;

    private boolean checked;

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

    @Override
    public long getFormNb() {
        return id.getForm();
    }

    @Override
    public String getValue() {
        return value;
    }

    void setChecked(boolean _checked) {
        checked = _checked;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }
}
