package code.mock;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdList;

public final class MockTextField extends MockTxtComponent implements AbsTextField {
    private final CustList<AbsActionListener> absActionListeners = new CustList<AbsActionListener>();
    private final IdList<AbsAdvActionListener> absAdvActionListeners = new IdList<AbsAdvActionListener>();
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
    public void addActionListener(AbsAdvActionListener _l) {
        addActionListenerMap(_l);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _l) {
        getAbsAdvActionListeners().add(_l);
    }

    @Override
    public void removeActionListener(AbsAdvActionListener _list) {
        removeActionListenerMap(_list);
    }

    @Override
    public void removeActionListenerMap(AbsAdvActionListener _list) {
        getAbsAdvActionListeners().removeObj(_list);
    }

    public CustList<AbsActionListener> getAbsActionListeners() {
        return absActionListeners;
    }

    public IdList<AbsAdvActionListener> getAbsAdvActionListeners() {
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
