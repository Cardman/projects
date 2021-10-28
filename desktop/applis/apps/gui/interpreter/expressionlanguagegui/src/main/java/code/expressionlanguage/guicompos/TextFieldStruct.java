package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsCustComponent;
import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class TextFieldStruct extends InputStruct {
    private AbsTextField textField;
    public TextFieldStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField();
    }
    public TextFieldStruct(String _className,Struct _txt, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField(getText(_txt));
    }
    public TextFieldStruct(String _className,NumberStruct _cols, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField(_cols.intStruct());
    }
    public TextFieldStruct(String _className,Struct _txt,Struct _cols, AbsCompoFactory _compo) {
        super(_className);
        textField = _compo.newTextField(getText(_txt),((NumberStruct)_cols).intStruct());
    }
    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(textField.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        textField.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    public void setText(Struct _t) {
        textField.setText(getText(_t));
    }

    private static String getText(Struct _txt) {
        if (_txt instanceof StringStruct) {
            return ((StringStruct)_txt).getInstance();
        }
        return "";
    }
    public Struct getText() {
        String txt_ = textField.getText();
        if (txt_ == null) {
            txt_ = "";
        }
        return new StringStruct(txt_);
    }

    @Override
    protected AbsCustComponent getComponent() {
        return textField;
    }

    public void addActionListener(Struct _arg) {
        if (_arg instanceof AbsActionListener) {
            textField.addActionListener((AbsActionListener)_arg);
        }
    }
}
