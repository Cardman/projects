package code.mock;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdList;

public abstract class MockAbsButton extends MockInput implements AbsButton {
    private final IdList<AbsActionListener> actionListeners = new IdList<AbsActionListener>();
    private final IdList<AbsAdvActionListener> advActionListeners = new IdList<AbsAdvActionListener>();
    private String text;
    protected MockAbsButton() {
        this("");
    }
    protected MockAbsButton(String _s) {
        text=_s;
    }

    @Override
    public void addActionListener(AbsActionListenerAct _c, AbsActionListener _list) {
        addActionListener(_list);
    }

    @Override
    public void addActionListener(AbsActionListener _l) {
        actionListeners.add(_l);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _l) {
        advActionListeners.add(_l);
    }

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        addActionListenerMap(_l);
    }

    public void removeActionListener(AbsActionListener _mouseListener) {
        actionListeners.removeObj(_mouseListener);
    }

    @Override
    public void removeActionListener(AbsAdvActionListener _list) {
        removeActionListenerMap(_list);
    }

    @Override
    public void removeActionListenerMap(AbsAdvActionListener _mouseListener) {
        advActionListeners.removeObj(_mouseListener);
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
