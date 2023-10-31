package code.mock;

import code.gui.AbsPlainButton;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdList;

public final class MockPlainButton extends MockInput implements AbsPlainButton {
    private final IdList<AbsActionListener> actionListeners = new IdList<AbsActionListener>();
    private final IdList<AbsAdvActionListener> advActionListeners = new IdList<AbsAdvActionListener>();
    private String text;
    public MockPlainButton() {
        this("");
    }
    public MockPlainButton(String _s) {
        text=_s;
    }
    @Override
    public void addActionListener(AbsActionListener _l) {
        addActionListenerMap(_l);
    }

    @Override
    public void addActionListenerMap(AbsActionListener _l) {
        actionListeners.add(_l);
    }

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        advActionListeners.add(_l);
    }

    public void removeActionListener(AbsActionListener _mouseListener) {
        removeActionListenerMap(_mouseListener);
    }

    @Override
    public void removeActionListenerMap(AbsActionListener _mouseListener) {
        actionListeners.removeObj(_mouseListener);
    }

    public CustList<AbsActionListener> getActionListeners() {
        return actionListeners;
    }

    public IdList<AbsAdvActionListener> getAdvActionListeners() {
        return advActionListeners;
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
