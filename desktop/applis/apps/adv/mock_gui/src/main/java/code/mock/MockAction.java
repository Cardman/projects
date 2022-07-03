package code.mock;

import code.gui.events.AbsActionListener;

public final class MockAction implements AbsActionListener {
    private final int number;
    private final MockWithAction withAction;

    public MockAction(int _nb, MockWithAction _ac) {
        this.number = _nb;
        this.withAction = _ac;
    }

    @Override
    public void action() {
        withAction.action(number);
    }
}
