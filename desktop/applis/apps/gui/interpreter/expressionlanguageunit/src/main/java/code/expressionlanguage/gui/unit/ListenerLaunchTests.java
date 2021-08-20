package code.expressionlanguage.gui.unit;

import code.gui.events.AbsActionListener;


public final class ListenerLaunchTests implements AbsActionListener {
    private WindowUnit mainWindow;
    private TestableFrame tested;

    public ListenerLaunchTests(WindowUnit _mainWindow, TestableFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }

    @Override
    public void action() {
        mainWindow.process(tested);
    }
}
