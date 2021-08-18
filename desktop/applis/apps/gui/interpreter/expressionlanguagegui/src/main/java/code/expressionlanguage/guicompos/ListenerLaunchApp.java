package code.expressionlanguage.guicompos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ListenerLaunchApp implements ActionListener {
    private WindowFull mainWindow;

    public ListenerLaunchApp(WindowFull _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        mainWindow.process();
    }
}
