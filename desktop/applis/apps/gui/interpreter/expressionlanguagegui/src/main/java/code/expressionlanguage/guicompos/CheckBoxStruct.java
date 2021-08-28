package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustCheckBox;
import code.gui.AbsCustComponent;
import code.gui.initialize.AbsCompoFactory;

public final class CheckBoxStruct extends InputStruct {
    private AbsCustCheckBox checkBox;

    protected CheckBoxStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        checkBox = _compo.newCustCheckBox();
    }

    protected CheckBoxStruct(String _className, Struct _txt, AbsCompoFactory _compo) {
        super(_className);
        checkBox = _compo.newCustCheckBox(getText(_txt));
    }

    protected CheckBoxStruct(String _className, Struct _txt, Struct _sel, AbsCompoFactory _compo) {
        super(_className);
        checkBox = _compo.newCustCheckBox(getText(_txt),BooleanStruct.isTrue(_sel));
    }

    public Struct getText() {
        String t_ = checkBox.getText();
        if (t_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(t_);
    }
    public void setText(Struct _txt) {
        checkBox.setText(getText(_txt));
    }
    private static String getText(Struct _txt) {
        if (_txt instanceof StringStruct) {
            return ((StringStruct)_txt).getInstance();
        }
        return null;
    }

    public Struct isSelected() {
        return BooleanStruct.of(checkBox.isSelected());
    }

    public void setSelected(Struct _b) {
        checkBox.setSelected(BooleanStruct.isTrue(_b));
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(checkBox.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        checkBox.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    @Override
    protected AbsCustComponent getComponent() {
        return checkBox;
    }
}
