package code.mock;

import code.gui.AbsTextField;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.gui.events.AbsAutoCompleteListener;
import code.util.CustList;

public final class MockTextField extends MockInput implements AbsTextField {
    private String text;
    private int caretPosition;
    private final CustList<AbsAutoCompleteListener> autoCompleteListeners = new CustList<AbsAutoCompleteListener>();
    private final CustList<AbsActionListener> absActionListeners = new CustList<AbsActionListener>();
    private final CustList<AbsAdvActionListener> absAdvActionListeners = new CustList<AbsAdvActionListener>();
    private int cols;
    public MockTextField() {
        this("");
    }
    public MockTextField(String _t) {
        text = _t;
    }
    public MockTextField(int _cols) {
        this("");
        cols = _cols;
    }
    public MockTextField(String _t,int _cols) {
        text = _t;
        cols = _cols;
    }
    @Override
    public void setText(String _s) {
        text = _s;
        for (AbsAutoCompleteListener a: autoCompleteListeners) {
            a.changedUpdate();
        }
    }

    @Override
    public String getText() {
        return text;
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

    public int getCaretPosition() {
        return caretPosition;
    }

    @Override
    public void setCaretPosition(int _i) {
        caretPosition = _i;
    }

    @Override
    public void setEditable(boolean _b) {
        setEnabled(_b);
    }
}
