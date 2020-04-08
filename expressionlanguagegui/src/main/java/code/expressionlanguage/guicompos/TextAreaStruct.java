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

    public void insert(Struct str, Struct pos) {
        textArea.insert(getText(str), ((NumberStruct)pos).intStruct());
    }

    public void append(Struct str) {
        textArea.append(getText(str));
    }

    @Override
    public Struct isEnabled() {
        return BooleanStruct.of(textArea.isEnabled());
    }

    @Override
    public void setEnabled(Struct _enabled) {
        textArea.setEnabled(((BooleanStruct)_enabled).getInstance());
    }

    public void setText(Struct t) {
        textArea.setText(getText(t));
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

    public void replaceRange(Struct str, Struct start, Struct end) {
        textArea.replaceRange(getText(str), ((NumberStruct)start).intStruct(), ((NumberStruct)end).intStruct());
    }

    public void replaceSelection(Struct str) {
        textArea.replaceSelection(getText(str));
    }

    public void select(Struct start, Struct end) {
        textArea.select(((NumberStruct)start).intStruct(), ((NumberStruct)end).intStruct());
    }

    public void selectAll() {
        textArea.selectAll();
    }

}
