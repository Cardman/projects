package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustCheckBox;
import code.gui.CustComponent;

public final class CheckBoxStruct extends InputStruct {
    private CustCheckBox checkBox;

    protected CheckBoxStruct(String _className) {
        super(_className);
        checkBox = new CustCheckBox();
    }

    protected CheckBoxStruct(String _className, Struct _txt) {
        super(_className);
        checkBox = new CustCheckBox(getText(_txt));
    }

    protected CheckBoxStruct(String _className, Struct _txt, Struct _sel) {
        super(_className);
        checkBox = new CustCheckBox(getText(_txt),BooleanStruct.isTrue(_sel));
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

    public void setSelected(Struct b) {
        checkBox.setSelected(BooleanStruct.isTrue(b));
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
    protected CustComponent getComponent() {
        return checkBox;
    }
}
