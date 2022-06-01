package code.gui.document;

import code.formathtml.util.FormInputCoords;
import code.gui.CustButtonGroup;

public final class FormInputCoordsButtonGroup {
    private final FormInputCoords inputCoords;
    private final CustButtonGroup group;

    public FormInputCoordsButtonGroup(FormInputCoords _i, CustButtonGroup _g) {
        this.inputCoords = _i;
        this.group = _g;
    }

    public FormInputCoords getInputCoords() {
        return inputCoords;
    }

    public CustButtonGroup getGroup() {
        return group;
    }
}
