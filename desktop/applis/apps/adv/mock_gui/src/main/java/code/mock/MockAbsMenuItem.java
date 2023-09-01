package code.mock;

import code.gui.AbsMenuItem;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdList;

public abstract class MockAbsMenuItem extends MockEnabledMenu implements AbsMenuItem {
    private final IdList<AbsActionListener> actionListeners = new IdList<AbsActionListener>();
    private final CustList<AbsAdvActionListener> advActionListeners = new CustList<AbsAdvActionListener>();

    protected MockAbsMenuItem(String _s) {
        super(_s);
    }

    @Override
    public void setAccelerator(int _a, int _b) {
        setEnabled(isEnabled());
    }

    @Override
    public void addActionListener(AbsActionListener _l) {
        actionListeners.add(_l);
    }

    @Override
    public void removeActionListener(AbsActionListener _list) {
        actionListeners.removeObj(_list);
    }

    public CustList<AbsActionListener> getActionListeners() {
        return actionListeners;
    }

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        advActionListeners.add(_l);
    }

    public CustList<AbsAdvActionListener> getAdvActionListeners() {
        return advActionListeners;
    }
}
