package code.mock;

import code.gui.AbsCustCheckBox;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdList;

public final class MockCustCheckBox extends MockInput implements AbsCustCheckBox {
    private final IdList<AbsActionListener> actionListeners = new IdList<AbsActionListener>();
    private final IdList<AbsAdvActionListener> actionAdvListeners = new IdList<AbsAdvActionListener>();
    private boolean selected;
    private String text;
    public MockCustCheckBox() {
        this("");
    }
    public MockCustCheckBox(String _s) {
        this(_s,false);
    }
    public MockCustCheckBox(String _s, boolean _b) {
        text=_s;
        selected = _b;
    }
    @Override
    public void addActionListener(AbsActionListener _l) {
        actionListeners.add(_l);
    }

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        addActionListenerMap(_l);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _l) {
        actionAdvListeners.add(_l);
    }

    @Override
    public void removeActionListener(AbsActionListener _l) {
        actionListeners.removeObj(_l);
    }

    @Override
    public void removeActionListener(AbsAdvActionListener _l) {
        removeActionListenerMap(_l);
    }

    @Override
    public void removeActionListenerMap(AbsAdvActionListener _l) {
        actionAdvListeners.removeObj(_l);
    }

    @Override
    public CustList<AbsActionListener> getActionListeners() {
        return actionListeners;
    }

    public void toggle() {
        setSelected(!isSelected());
    }
    @Override
    public void setSelected(boolean _b) {
        boolean prev_ = selected;
        selected = _b;
        if (prev_ != _b) {
            for (AbsActionListener a: actionListeners) {
                a.action();
            }
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setText(String _s) {
        text = _s;
    }

    @Override
    public String getText() {
        return text;
    }
}
