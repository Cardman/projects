package code.mock;

import code.gui.AbsTextArea;
import code.gui.images.MetaPoint;

public final class MockTextArea extends MockInput implements AbsTextArea {
    private final StringBuilder builder = new StringBuilder();
    private String selected = "";
    private int selectionStart;
    private int selectionEnd;
    private int tabSize;
    private int rows;
    private int cols;

    public MockTextArea() {
        this("");
    }
    public MockTextArea(int _r,int _c) {
        this("",_r,_c);
    }
    public MockTextArea(String _txt) {
        builder.append(_txt);
    }
    public MockTextArea(String _txt,int _r,int _c) {
        builder.append(_txt);
        rows = _r;
        cols = _c;
    }
    @Override
    public void insert(String _s, int _i) {
        builder.insert(_i,_s);
    }

    @Override
    public void forceInsert(String _s, int _i) {
        builder.insert(_i,_s);
    }

    @Override
    public void replaceRange(String _s, int _start, int _end) {
        builder.replace(_start,_end,_s);
    }

    @Override
    public void forceReplaceRange(String _s, int _start, int _end) {
        builder.replace(_start,_end,_s);
    }

    @Override
    public void replaceSelection(String _s) {
        builder.replace(selectionStart,selectionEnd,_s);
        selected = "";
    }

    @Override
    public void append(String _s) {
        builder.append(_s);
    }

    @Override
    public void setText(String _s) {
        builder.delete(0,builder.length());
        builder.append(_s);
    }

    @Override
    public String getText() {
        return builder.toString();
    }

    @Override
    public String getSelectedText() {
        return selected;
    }

    @Override
    public void setSelectionStart(int _i) {
        select(_i,selectionEnd);
    }

    @Override
    public void setSelectionEnd(int _i) {
        select(selectionStart,_i);
    }

    @Override
    public void setTabSize(int _t) {
        tabSize = _t;
    }

    @Override
    public int getTabSize() {
        return tabSize;
    }

    @Override
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

    @Override
    public void selectAll() {
        selected = getText();
    }

    @Override
    public void setEditable(boolean _b) {
        setEnabled(_b);
    }

    @Override
    public int viewToModel(MetaPoint _m) {
        return 0;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
