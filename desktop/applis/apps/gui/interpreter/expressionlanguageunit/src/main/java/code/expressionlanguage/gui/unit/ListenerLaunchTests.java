package code.expressionlanguage.gui.unit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ListenerLaunchTests implements ActionListener {
    private WindowUnit mainWindow;
    private TestableFrame tested;

    public ListenerLaunchTests(WindowUnit _mainWindow, TestableFrame _tested) {
        mainWindow = _mainWindow;
        tested = _tested;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.process(tested);
    }
}
