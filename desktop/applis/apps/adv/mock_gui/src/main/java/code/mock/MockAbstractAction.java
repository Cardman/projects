package code.mock;

import code.gui.events.AbsActionListener;

public final class MockAbstractAction extends MockAbstractActionCommon implements AbsActionListener {

    private final AbsActionListener actionListener;

    public MockAbstractAction(AbsActionListener _a) {
        this.actionListener = _a;
    }

    @Override
    public void action() {
        if (!isEnabled()) {
            return;
        }
        actionListener.action();
    }
}
