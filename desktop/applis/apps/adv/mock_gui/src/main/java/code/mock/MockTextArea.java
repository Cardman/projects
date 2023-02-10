package code.mock;

import code.gui.AbsTextArea;

public final class MockTextArea extends MockTxtComponent implements AbsTextArea {
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
        getBuilder().append(_txt);
    }
    public MockTextArea(String _txt,int _r,int _c) {
        getBuilder().append(_txt);
        rows = _r;
        cols = _c;
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
    public void setEditable(boolean _b) {
        setEnabled(_b);
    }


    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
