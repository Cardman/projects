package code.mock;

import code.gui.AbsTxtComponent;
import code.gui.images.MetaPoint;

public abstract class MockTxtComponent extends MockInput implements AbsTxtComponent {
    private final StringBuilder builder = new StringBuilder();
    private String selected = "";
    private int selectionStart;
    private int selectionEnd;
    private int caretColor;
    private int selectionColor;
    private int selectedTextColor;

    @Override
    public int getCaretPosition() {
        return selectionStart;
    }

    @Override
    public void setCaretPosition(int _position) {
        selectionStart = _position;
    }

    @Override
    public void moveCaretPosition(int _pos) {
        selectionEnd = _pos;
    }

    public void insert(String _s, int _i) {
        builder.insert(_i,_s);
    }

    public void replaceRange(String _s, int _start, int _end) {
        builder.replace(_start,_end,_s);
    }

    public void replaceSelection(String _s) {
        builder.replace(selectionStart,selectionEnd,_s);
        selected = "";
    }

    public void append(String _s) {
        builder.append(_s);
    }

    public void setText(String _s) {
        builder.delete(0,builder.length());
        builder.append(_s);
    }

    public String getText() {
        return builder.toString();
    }

    public String getSelectedText() {
        return selected;
    }

    public void setSelectionStart(int _i) {
        select(_i,selectionEnd);
    }

    public void setSelectionEnd(int _i) {
        select(selectionStart,_i);
    }

    public void select(int _start, int _end) {
        selectionStart = _start;
        selectionEnd = _end;
        if (_start < 0 || _end >= builder.length() || _end < _start) {
            selected = "";
            selectionStart = 0;
            selectionEnd = 0;
        } else {
            selected = builder.substring(_start, _end);
        }
    }

    public void selectAll() {
        selectionStart = 0;
        selectionEnd = builder.length();
        selected = getText();
    }

    public int getCaretColor() {
        return caretColor;
    }

    public void setCaretColor(int _c) {
        this.caretColor = _c;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int _c) {
        this.selectedTextColor = _c;
    }

    public int getSelectionColor() {
        return selectionColor;
    }

    public void setSelectionColor(int _c) {
        this.selectionColor = _c;
    }

    @Override
    public int viewToModel(MetaPoint _m) {
        return 0;
    }
    public StringBuilder getBuilder() {
        return builder;
    }
}
