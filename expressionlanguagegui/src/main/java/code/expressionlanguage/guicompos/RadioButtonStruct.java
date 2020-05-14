package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.RadioButton;

import javax.swing.event.ChangeListener;

public final class RadioButtonStruct extends InputStruct {
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
            radioButton = new RadioButton(((StringStruct)_text).getInstance(),BooleanStruct.of(true).sameReference(_s));
        } else {
            radioButton = new RadioButton(null,BooleanStruct.of(true).sameReference(_s));
        }

    }

    public void addChangeListener(Struct _l) {
        if (_l instanceof ChangeListener) {
            radioButton.addChangeListener((ChangeListener) _l);
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
        return BooleanStruct.of(radioButton.isSelected());
    }

    public void setSelected(Struct b) {
        radioButton.setSelected(BooleanStruct.of(true).sameReference(b));
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(radioButton.isEnabled());
    }

    @Override
    public void setEnabled(Struct b) {
        radioButton.setEnabled(BooleanStruct.of(true).sameReference(b));
    }

    @Override
    protected CustComponent getComponent() {
        return getRadioButton();
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}
