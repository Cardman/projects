package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.RadioButton;
import code.gui.events.AbsChangeListener;

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
            radioButton = new RadioButton(((StringStruct)_text).getInstance(),BooleanStruct.isTrue(_s));
        } else {
            radioButton = new RadioButton(null,BooleanStruct.isTrue(_s));
        }

    }

    public void addChangeListener(Struct _l) {
        if (_l instanceof AbsChangeListener) {
            radioButton.addChangeListener((AbsChangeListener) _l);
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

    public void setSelected(Struct _b) {
        radioButton.setSelected(BooleanStruct.isTrue(_b));
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(radioButton.isEnabled());
    }

    @Override
    public void setEnabled(Struct _b) {
        radioButton.setEnabled(BooleanStruct.isTrue(_b));
    }

    @Override
    protected CustComponent getComponent() {
        return getRadioButton();
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }
}
