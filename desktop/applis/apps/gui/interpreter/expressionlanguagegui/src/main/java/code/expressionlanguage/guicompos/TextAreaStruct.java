package code.expressionlanguage.guicompos;

import code.expressionlanguage.structs.*;
import code.gui.CustComponent;
import code.gui.TextArea;

public final class TextAreaStruct extends InputStruct {
    private TextArea textArea;
    protected TextAreaStruct(String _className) {
        super(_className);
        textArea = new TextArea();
    }
    protected TextAreaStruct(String _className, Struct _txt) {
        super(_className);
        textArea = new TextArea(getText(_txt));
    }
    protected TextAreaStruct(String _className, Struct _rows, Struct _cols) {
        super(_className);
        textArea = new TextArea(((NumberStruct)_rows).intStruct(),((NumberStruct)_cols).intStruct());
    }
    protected TextAreaStruct(String _className, Struct _txt, Struct _rows, Struct _cols) {
        super(_className);
        textArea = new TextArea(getText(_txt),((NumberStruct)_rows).intStruct(),((NumberStruct)_cols).intStruct());
    }

    public void insert(Struct _str, Struct _pos) {
        textArea.insert(getText(_str), ((NumberStruct)_pos).intStruct());
    }

    public void append(Struct _str) {
        textArea.append(getText(_str));
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(textArea.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        textArea.setEnabled(BooleanStruct.isTrue(_enabled));
    }

    public void setText(Struct _t) {
        textArea.setText(getText(_t));
    }

    private static String getText(Struct _txt) {
        if (_txt instanceof StringStruct) {
            return ((StringStruct)_txt).getInstance();
        }
        return "";
    }
    public Struct getText() {
        String txt_ = textArea.getText();
        if (txt_ == null) {
            txt_ = "";
        }
        return new StringStruct(txt_);
    }

    @Override
    protected CustComponent getComponent() {
        return textArea;
    }

    public Struct getSelectedText() {
        String selectedText_ = textArea.getSelectedText();
        if (selectedText_ == null) {
            selectedText_ = "";
        }
        return new StringStruct(selectedText_);
    }

    public void setSelectionStart(Struct _arg) {
        textArea.setSelectionStart(((NumberStruct)_arg).intStruct());
    }

    public void setSelectionEnd(Struct _arg) {
        textArea.setSelectionEnd(((NumberStruct)_arg).intStruct());
    }

    public void setTabSize(Struct _arg) {
        textArea.setTabSize(((NumberStruct)_arg).intStruct());
    }

    public Struct getTabSize() {
        return new IntStruct(textArea.getTabSize());
    }

    public void replaceRange(Struct _str, Struct _start, Struct _end) {
        textArea.replaceRange(getText(_str), ((NumberStruct)_start).intStruct(), ((NumberStruct)_end).intStruct());
    }

    public void replaceSelection(Struct _str) {
        textArea.replaceSelection(getText(_str));
    }

    public void select(Struct _start, Struct _end) {
        textArea.select(((NumberStruct)_start).intStruct(), ((NumberStruct)_end).intStruct());
    }

    public void selectAll() {
        textArea.selectAll();
    }

}
