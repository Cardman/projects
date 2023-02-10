package code.mock;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsAutoCompleteListener;
import code.util.CustList;

public final class MockTextField extends MockTxtComponent implements AbsTextField {
    private final CustList<AbsAutoCompleteListener> autoCompleteListeners = new CustList<AbsAutoCompleteListener>();
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
    public void setText(String _s) {
        super.setText(_s);
        for (AbsAutoCompleteListener a: autoCompleteListeners) {
            a.changedUpdate();
        }
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

    @Override
    public void addAutoComplete(AbsAutoCompleteListener _l) {
        autoCompleteListeners.add(_l);
    }

    public CustList<AbsAutoCompleteListener> getAutoCompleteListeners() {
        return autoCompleteListeners;
    }

    public int getCols() {
        return cols;
    }


    @Override
    public void setEditable(boolean _b) {
        setEnabled(_b);
    }
}
