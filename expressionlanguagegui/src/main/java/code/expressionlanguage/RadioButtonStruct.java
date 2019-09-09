package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.RadioButton;

public final class RadioButtonStruct extends CustComponentStruct {
    private RadioButton radioButton;
    protected RadioButtonStruct(String _className) {
        super(_className);
        radioButton = new RadioButton();
    }
    protected RadioButtonStruct(String _className,Struct _text) {
        super(_className);
        if (_text instanceof StringStruct) {
            radioButton = new RadioButton(((StringStruct)_text).getInstance());
        } else {
            radioButton = new RadioButton(null);
        }
    }
    protected RadioButtonStruct(String _className, Struct _text, Struct _s) {
        super(_className);
        if (_text instanceof StringStruct) {
            radioButton = new RadioButton(((StringStruct)_text).getInstance(),((BooleanStruct)_s).getInstance());
        } else {
            radioButton = new RadioButton(null,((BooleanStruct)_s).getInstance());
        }

    }

    public Struct getText() {
        return new StringStruct(radioButton.getText());
    }

    public void setText(Struct _text) {
        if (_text instanceof StringStruct) {
            radioButton.setText(((StringStruct)_text).getInstance());
        } else {
            radioButton.setText(null);
        }

    }

    public Struct isSelected() {
        return new BooleanStruct(radioButton.isSelected());
    }

    public void setSelected(Struct b) {
        radioButton.setSelected(((BooleanStruct)b).getInstance());
    }

    public Struct isEnabled() {
        return new BooleanStruct(radioButton.isEnabled());
    }

    public void setEnabled(Struct b) {
        radioButton.setEnabled(((BooleanStruct)b).getInstance());
    }

    @Override
    protected CustComponent getComponent() {
        return getRadioButton();
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}
