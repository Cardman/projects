package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsRadioButton;
import code.gui.events.AbsChangeListener;
import code.gui.initialize.AbsCompoFactory;

public final class RadioButtonStruct extends InputStruct {
    private final AbsRadioButton radioButton;
    public RadioButtonStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        radioButton = _compo.newRadioButton();
    }
    public RadioButtonStruct(String _className,Struct _text, AbsCompoFactory _compo) {
        super(_className);
        if (_text instanceof StringStruct) {
            radioButton = _compo.newRadioButton(((StringStruct)_text).getInstance());
        } else {
            radioButton = _compo.newRadioButton(null);
        }
    }
    public RadioButtonStruct(String _className, Struct _text, Struct _s, AbsCompoFactory _compo) {
        super(_className);
        if (_text instanceof StringStruct) {
            radioButton = _compo.newRadioButton(((StringStruct)_text).getInstance(),BooleanStruct.isTrue(_s));
        } else {
            radioButton = _compo.newRadioButton(null,BooleanStruct.isTrue(_s));
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
    protected AbsCustComponent getComponent() {
        return getRadioButton();
    }

    public AbsRadioButton getRadioButton() {
        return radioButton;
    }
}
