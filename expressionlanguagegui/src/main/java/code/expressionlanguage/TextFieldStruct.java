package code.expressionlanguage;

import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.CustComponent;
import code.gui.TextField;

import java.awt.event.ActionListener;

public final class TextFieldStruct extends InputStruct {
    private TextField textField;
    protected TextFieldStruct(String _className) {
        super(_className);
        textField = new TextField();
    }
    protected TextFieldStruct(String _className,Struct _txt) {
        super(_className);
        textField = new TextField(getText(_txt));
    }
    protected TextFieldStruct(String _className,NumberStruct _cols) {
        super(_className);
        textField = new TextField(_cols.intStruct());
    }
    protected TextFieldStruct(String _className,Struct _txt,Struct _cols) {
        super(_className);
        textField = new TextField(getText(_txt),((NumberStruct)_cols).intStruct());
    }
    @Override
    public Struct isEnabled() {
        return new BooleanStruct(textField.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        textField.setEnabled(((BooleanStruct)_enabled).getInstance());
    }

    public void setText(Struct t) {
        textField.setText(getText(t));
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
    protected CustComponent getComponent() {
        return textField;
    }

    public void addActionListener(Struct _arg) {
        if (_arg instanceof ActionListener) {
            textField.addActionListener((ActionListener) _arg);
        }
    }
}
