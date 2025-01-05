package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.*;
import code.gui.*;
import code.gui.initialize.*;

public final class TextAreaStruct extends TxtComponentStruct {
    private final AbsTextArea textArea;
    public TextAreaStruct(String _className, AbsCompoFactory _compo) {
        super(_className);
        textArea = _compo.newTextArea();
    }
    public TextAreaStruct(String _className, Struct _txt, AbsCompoFactory _compo) {
        super(_className);
        textArea = _compo.newTextArea(getText(_txt));
    }
    public TextAreaStruct(String _className, Struct _rows, Struct _cols, AbsCompoFactory _compo) {
        super(_className);
        textArea = _compo.newTextArea(((NumberStruct)_rows).intStruct(),((NumberStruct)_cols).intStruct());
    }
    public TextAreaStruct(String _className, Struct _txt, Struct _rows, Struct _cols, AbsCompoFactory _compo) {
        super(_className);
        textArea = _compo.newTextArea(getText(_txt),((NumberStruct)_rows).intStruct(),((NumberStruct)_cols).intStruct());
    }

    public void append(Struct _str) {
        textArea.append(getText(_str));
    }

    @Override
    protected AbsTxtComponent component() {
        return textArea;
    }

    public void setTabSize(Struct _arg) {
        textArea.setTabSize(((NumberStruct)_arg).intStruct());
    }

    public Struct getTabSize() {
        return new IntStruct(textArea.getTabSize());
    }

    public void replaceRange(Struct _str, Struct _start, Struct _end) {
        if ( ((NumberStruct)_start).intStruct() < 0) {
            return;
        }
        if (((NumberStruct)_end).intStruct() <  ((NumberStruct)_start).intStruct()) {
            return;
        }
        textArea.replaceRange(getText(_str), ((NumberStruct)_start).intStruct(), ((NumberStruct)_end).intStruct());
    }

}
