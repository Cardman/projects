package code.expressionlanguage.guicompos;

import code.expressionlanguage.common.*;
import code.expressionlanguage.structs.*;
import code.gui.*;

public abstract class TxtComponentStruct extends CustComponentStruct {
    protected TxtComponentStruct(String _className) {
        super(_className);
    }

    public void setText(Struct _t) {
        component().setText(getText(_t));
    }

    protected static String getText(Struct _txt) {
        return NumParsers.getString(_txt).getInstance();
    }
    public Struct getText() {
        String txt_ = component().getText();
        return new StringStruct(txt_);
    }

    public Struct getSelectedText() {
        String selectedText_ = component().getSelectedText();
        return new StringStruct(selectedText_);
    }

    public void setSelectionStart(Struct _arg) {
        component().setSelectionStart(((NumberStruct)_arg).intStruct());
    }

    public void setSelectionEnd(Struct _arg) {
        component().setSelectionEnd(((NumberStruct)_arg).intStruct());
    }

    public void select(Struct _start, Struct _end) {
        component().select(((NumberStruct)_start).intStruct(), ((NumberStruct)_end).intStruct());
    }

    public void insert(Struct _str, Struct _pos) {
        if ( ((NumberStruct)_pos).intStruct() < 0) {
            return;
        }
        component().insert(getText(_str), ((NumberStruct)_pos).intStruct());
    }

    public void remove(Struct _str, Struct _pos) {
        if (((NumberStruct)_str).intStruct() < 0 ||((NumberStruct)_pos).intStruct() < 0) {
            return;
        }
        component().remove(((NumberStruct)_str).intStruct(), ((NumberStruct)_pos).intStruct());
    }

    public void replaceSelection(Struct _str) {
        component().replaceSelection(getText(_str));
    }
    public void selectAll() {
        component().selectAll();
    }
    @Override
    protected AbsCustComponent getComponent() {
        return component();
    }

    protected abstract AbsTxtComponent component();
}
