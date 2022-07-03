package code.mock;

import code.gui.AbsMenuItemCommon;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;

public abstract class MockAbsMenuItem extends MockEnabledMenu implements AbsMenuItemCommon {
    private final CustList<AbsActionListener> actionListeners = new CustList<AbsActionListener>();
    private final CustList<AbsAdvActionListener> advActionListeners = new CustList<AbsAdvActionListener>();

    protected MockAbsMenuItem(String _s) {
        super(_s);
    }
    @Override
    public void setAccelerator(char _c) {
        setEnabled(isEnabled());
    }

    @Override
    public void setAccelerator(String _s) {
        setAccelerator(' ');
    }

    @Override
    public void setAccelerator(int _a, int _b) {
        setAccelerator(' ');
    }

    @Override
    public void addActionListener(AbsActionListener _l) {
        actionListeners.add(_l);
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
