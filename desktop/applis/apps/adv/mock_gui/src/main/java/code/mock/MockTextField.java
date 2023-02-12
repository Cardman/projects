package code.mock;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;

public final class MockTextField extends MockTxtComponent implements AbsTextField {
    private final CustList<AbsActionListener> absActionListeners = new CustList<AbsActionListener>();
    private final CustList<AbsAdvActionListener> absAdvActionListeners = new CustList<AbsAdvActionListener>();
    private int cols;
    public MockTextField() {
        this("");
    }
    public MockTextField(String _t) {
        setText(_t);
    }
    public MockTextField(int _cols) {
        this("");
        cols = _cols;
    }
    public MockTextField(String _t,int _cols) {
        setText(_t);
        cols = _cols;
    }

    @Override
    public void addActionListener(AbsActionListener _l) {
        absActionListeners.add(_l);
    }

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        absAdvActionListeners.add(_l);
    }

    public CustList<AbsActionListener> getAbsActionListeners() {
        return absActionListeners;
    }

    public CustList<AbsAdvActionListener> getAbsAdvActionListeners() {
        return absAdvActionListeners;
    }

    public int getCols() {
        return cols;
    }


    @Override
    public void setEditable(boolean _b) {
        setEnabled(_b);
    }
}
